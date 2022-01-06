package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.dto.PaymentDTO;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PromotionService promotionService;

    @Override
    public PaymentDTO payment(Map<String, Object> request) throws ParseException {
        PaymentDTO paymentDTO = new PaymentDTO();
        int accountNumber = (int) request.get("accountNumber");
        double payAmount = (double) request.get("payAmount");
        double receivedMoney = (double) request.get("receivedMoney");
        String merchantId = (String) request.get("merchantId");
        LocalDateTime localDateTime = LocalDateTime.now();
        //Get Entity
        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByAccountNumber(accountNumber);
        MerchantEntity merchantEntity = merchantService.getMerchantByMerchantId(merchantId);
        List<PromotionsEntity> promotionsEntityList = promotionService.findByMasterId(merchantEntity.getMaId());
        //Get DTO
        UserDTO user = userService.getUserByMasterAccId(userAccountEntity.getMaId());

        Map<String, Object> promotion = havePromotion(promotionsEntityList, payAmount, receivedMoney);
        boolean isPromotion = (boolean) promotion.get("isPromotion");
        paymentDTO.setCreatedOn(localDateTime);
        paymentDTO.setPayBy(user.getName());
        paymentDTO.setPayTo(merchantEntity.getName());
        paymentDTO.setRecievedMoney(receivedMoney);
        if (isPromotion) {
            paymentDTO.setPromotionType((String) promotion.get("promotionType"));
            paymentDTO.setPromotionAmount(promotion.get("promotionAmount") +"("+ promotion.get("promotionValueType") +")");
            paymentDTO.setPromotionTotal((double) promotion.get("finalPromotionAmount"));
            paymentDTO.setAmount(payAmount);
            paymentDTO.setChange((double) promotion.get("change"));
        } else {
            paymentDTO.setAmount(payAmount);
            paymentDTO.setChange(receivedMoney - payAmount);
        }
        return paymentDTO;
    }

    private PaymentDTO pay(UserAccountEntity userAccountEntity, MerchantEntity merchantEntity, double finalAmount) {
        PaymentDTO paymentDTO = new PaymentDTO();
        UserAccountEntity userAccountOfMerchant = userAccountService.getUserAccountByMasterAccId(merchantEntity.getMaId()).get(0);
        transactionService.transferMoney(userAccountEntity.getAccountNumber(), finalAmount, userAccountOfMerchant.getAccountNumber());
        return paymentDTO;
    }

    private Map<String, Object> havePromotion(List<PromotionsEntity> promotionsEntityList, double payAmount, double receivedMoney) throws ParseException {
        Map<String, Object> obj = new HashMap<>();
        for (PromotionsEntity each : promotionsEntityList) {
            double minPayment = Double.parseDouble(each.getMinPayment());
            double maxPayment = Double.parseDouble(each.getMaxPayment());
            LocalDateTime startDate = LocalDateTime.parse(each.getDurationEntity().getStart());
            LocalDateTime endDate = LocalDateTime.parse(each.getDurationEntity().getEnd());
            LocalDateTime now = LocalDateTime.now();
            long diff_from_start = ChronoUnit.HOURS.between(startDate, now);
            long diff_from_end = ChronoUnit.HOURS.between(now, endDate);
            if (diff_from_start > 0 && diff_from_end > 0) {
                if (minPayment < payAmount && payAmount <= maxPayment) {
                    return getPromotion(each.getPromotionType(), each.getPromotionValueType(), each.getPromotionAmount(), payAmount, receivedMoney);
                }
            }
        }
        obj.put("isPromotion", false);
        return obj;
    }

    private Map<String, Object> getPromotion(String promotionType, String promotionValueType, String promotionAmount, double payAmount, double receivedMoney) {

        Map<String, Object> obj = new HashMap<>();
        if (promotionType.contains("discount")) {
            obj = promotionAndAmount(promotionValueType, promotionAmount, payAmount, receivedMoney);
        }
        if (promotionType.contains("cash-back")) {
            obj = promotionAndAmount(promotionValueType, promotionAmount, payAmount, receivedMoney);
        }
        obj.put("promotionType", promotionType);
        obj.put("promotionValueType",promotionValueType);
        obj.put("promotionAmount", promotionAmount);
        obj.put("isPromotion", true);

        return obj;
    }

    private Map<String, Object> promotionAndAmount(String promotionValueType, String promotionAmount, double payAmount, double receivedMoney) {
        Map<String, Object> obj = new HashMap<>();
        double finalPromotionAmount = 0.0;
        double finalAmount = 0.0;
        double PromotionValue = Double.parseDouble(promotionAmount.replace("%", ".0")) / 100.0;
        double change;

        switch (promotionValueType) {
            case "percentage":
                finalPromotionAmount = payAmount * PromotionValue;
                finalAmount = payAmount - finalPromotionAmount;
                break;
            case "fixed-amount":
                finalPromotionAmount =  Double.parseDouble(promotionAmount);
                finalAmount = payAmount - finalPromotionAmount;
                break;
        }
        change = receivedMoney - finalAmount;
        obj.put("change", change);
        obj.put("finalPromotionAmount", finalPromotionAmount);
        obj.put("finalAmount", finalAmount);
        return obj;
    }

}
