package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.dto.PaymentDTO;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
import com.rofat.MySQLWorkBench.repository.MerchantRepo;
import com.rofat.MySQLWorkBench.repository.PromotionsRepo;
import com.rofat.MySQLWorkBench.repository.UserAccRepo;
import com.rofat.MySQLWorkBench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TransactionService transactionService;
    @Autowired
    private PromotionsRepo promotionsRepo;
    @Autowired
    private UserAccRepo userAccRepo;
    @Autowired
    private MerchantRepo merchantRepo;


    double rate = 4000.0;

    @Override
    public PaymentDTO payment(Map<String, Object> request) {
        PaymentDTO paymentDTO = new PaymentDTO();
        int accountNumber = (int) request.get("accountNumber");
        double payAmount = (double) request.get("amount");
        String merchantId = (String) request.get("merchantId");
        LocalDateTime localDateTime = LocalDateTime.now();

        //Get Entity
        UserAccountEntity userAccountEntity = userAccRepo.getUserAccountByAccountNumber(accountNumber);
        MerchantEntity merchantEntity = merchantRepo.findMerchantEntityByMerchantId(merchantId);
        List<PromotionsEntity> promotionsEntityList = promotionsRepo.findPromotionsByMaid(merchantEntity.getMaId());
        //Get DTO
        UserDTO user = userService.getUserByMasterAccId(userAccountEntity.getMaId());

        paymentDTO.setCreatedOn(localDateTime);
        paymentDTO.setPayBy(user.getName());
        paymentDTO.setPayTo(merchantEntity.getName());
        paymentDTO.setAmount(payAmount);
        if (payAmount > 0) {
            Map<String, Object> promotion = havePromotion(promotionsEntityList, payAmount);
            boolean isPromotion = (boolean) promotion.get("isPromotion");
            //Check for promotion
            if (isPromotion) {
                paymentDTO.setPromotionType((String) promotion.get("promotionType"));
                paymentDTO.setPromotionAmount(promotion.get("promotionAmount") + "(" + promotion.get("promotionValueType") + ")");
                paymentDTO.setPromotionTotal((double) promotion.get("finalPromotionAmount"));
                payAmount = (double) promotion.get("finalAmount");

            } else {
                paymentDTO.setPromotionType("N/A");
                paymentDTO.setPromotionAmount("N/A");
            }
            paymentDTO.setMessage("success");
            //Transfer money
            if (!pay(userAccountEntity, merchantEntity, payAmount)) {
                paymentDTO.setMessage("Unable to transfer");
            }

        } else {
            paymentDTO.setMessage("Invalid amount");
        }
        return paymentDTO;
    }

    private boolean pay(UserAccountEntity userAccountEntity, MerchantEntity merchantEntity, double payAmount) {

        double amountKHR;
        double amountUSD;
        boolean isNotComplete = true;
        if (userAccountEntity.getCurrencyType() == CurrencyType.USD) {
            amountKHR = payAmount * rate;
        } else {
            amountKHR = payAmount;
        }
        if (userAccountEntity.getCurrencyType() == CurrencyType.KHR) {
            amountUSD = payAmount / rate;
        } else {
            amountUSD = payAmount;
        }
        List<UserAccountEntity> userAccountOfMerchant = userAccRepo.getUserAccountByMaId(merchantEntity.getMaId());
        for (UserAccountEntity each : userAccountOfMerchant) {
            if (each.getCurrencyType() == userAccountEntity.getCurrencyType()) {
                if (transactionService.transferMoney(userAccountEntity.getAccountNumber(), userAccountEntity.getCurrencyType(), amountUSD, amountKHR, each.getAccountNumber(), each.getCurrencyType())) {
                    return true;
                }
                isNotComplete = false;
            }
        }
        if (isNotComplete) {
            for (UserAccountEntity each : userAccountOfMerchant) {
                if (each.getCurrencyType() != userAccountEntity.getCurrencyType()) {
                    if (transactionService.transferMoney(userAccountEntity.getAccountNumber(), userAccountEntity.getCurrencyType(), amountUSD, amountKHR, each.getAccountNumber(), each.getCurrencyType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Map<String, Object> havePromotion(List<PromotionsEntity> promotionsEntityList, double amount) {
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
                if (minPayment < amount && amount <= maxPayment) {
                    return getPromotion(each.getPromotionType(), each.getPromotionValueType(), each.getPromotionAmount(), amount);
                }
            }
        }
        obj.put("isPromotion", false);
        return obj;
    }

    private Map<String, Object> getPromotion(String promotionType, String promotionValueType, String promotionAmount, double amount) {

        Map<String, Object> obj = new HashMap<>();
        if (promotionType.contains("discount")) {
            obj = promotionAndAmount(promotionValueType, promotionAmount, amount);
        }
        if (promotionType.contains("cash-back")) {
            obj = promotionAndAmount(promotionValueType, promotionAmount, amount);
        }
        obj.put("promotionType", promotionType);
        obj.put("promotionValueType", promotionValueType);
        obj.put("promotionAmount", promotionAmount);
        obj.put("isPromotion", true);

        return obj;
    }

    private Map<String, Object> promotionAndAmount(String promotionValueType, String promotionAmount, double amount) {
        Map<String, Object> obj = new HashMap<>();
        double finalPromotionAmount = 0.0;
        double finalAmount = 0.0;
        double PromotionValue = Double.parseDouble(promotionAmount.replace("%", ".0")) / 100.0;

        switch (promotionValueType) {
            case "percentage":
                finalPromotionAmount = amount * PromotionValue;
                finalAmount = amount - finalPromotionAmount;
                break;
            case "fixed-amount":
                finalPromotionAmount = Double.parseDouble(promotionAmount);
                finalAmount = amount - finalPromotionAmount;
                break;
        }

        obj.put("finalPromotionAmount", finalPromotionAmount);
        obj.put("finalAmount", finalAmount);
        return obj;
    }
}
