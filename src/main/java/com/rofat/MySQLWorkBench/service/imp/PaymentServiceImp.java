package com.rofat.MySQLWorkBench.service.imp;

import com.rofat.MySQLWorkBench.constant.CurrencyType;
import com.rofat.MySQLWorkBench.dto.PaymentDTO;
import com.rofat.MySQLWorkBench.dto.UserDTO;
import com.rofat.MySQLWorkBench.model.MerchantEntity;
import com.rofat.MySQLWorkBench.model.PromotionsEntity;
import com.rofat.MySQLWorkBench.model.UserAccountEntity;
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
    private UserAccountService userAccountService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PromotionService promotionService;

    double rate = 4000.0;

    @Override
    public PaymentDTO payment(Map<String, Object> request) {
        PaymentDTO paymentDTO = new PaymentDTO();
        int accountNumber = (int) request.get("accountNumber");
        double payAmount = (double) request.get("amount");
        String amountCurrency = (String) request.get("amountCurrency");
        double receivedMoney = (double) request.get("receivedMoney");
        String merchantId = (String) request.get("merchantId");
        LocalDateTime localDateTime = LocalDateTime.now();

        //Get Entity
        UserAccountEntity userAccountEntity = userAccountService.getUserAccountByAccountNumber(accountNumber);
        MerchantEntity merchantEntity = merchantService.getMerchantByMerchantId(merchantId);
        List<PromotionsEntity> promotionsEntityList = promotionService.findByMasterId(merchantEntity.getMaId());
        //Get DTO
        UserDTO user = userService.getUserByMasterAccId(userAccountEntity.getMaId());

        paymentDTO.setCreatedOn(localDateTime);
        paymentDTO.setPayBy(user.getName());
        paymentDTO.setPayTo(merchantEntity.getName());
        paymentDTO.setAmount(payAmount);
        paymentDTO.setAmountCurrency(amountCurrency);
        paymentDTO.setRecieveAmount(receivedMoney);
        paymentDTO.setReceiveAmountCurrency(userAccountEntity.getCurrencyType().toString());


        if (isValidReceiveAmount(payAmount, receivedMoney, userAccountEntity, amountCurrency)) {
            Map<String, Object> promotion = havePromotion(promotionsEntityList, userAccountEntity.getCurrencyType(), amountCurrency, payAmount, receivedMoney);
            boolean isPromotion = (boolean) promotion.get("isPromotion");
            //Check for promotion
            if (isPromotion) {
                paymentDTO.setPromotionType((String) promotion.get("promotionType"));
                paymentDTO.setPromotionAmount(promotion.get("promotionAmount") + "(" + promotion.get("promotionValueType") + ")");
                paymentDTO.setPromotionTotal((double) promotion.get("finalPromotionAmount"));
                paymentDTO.setChange((double) promotion.get("change"));
                paymentDTO.setMessage("success");
            } else {
                paymentDTO.setChange(receivedMoney - payAmount);
            }
            //Transfer money
            pay(userAccountEntity, merchantEntity, payAmount, amountCurrency);
        } else {
            paymentDTO.setMessage("Invalid amount");
        }
        return paymentDTO;
    }

    private void pay(UserAccountEntity userAccountEntity, MerchantEntity merchantEntity, double payAmount, String amountCurrency) {

        double amountKHR;
        double amountUSD;
        boolean isNotComplete = true;
        if (CurrencyType.USD.toString().contains(amountCurrency)) {
            amountKHR = payAmount * rate;
        } else {
            amountKHR = payAmount;
        }
        if (CurrencyType.KHR.toString().contains(amountCurrency)) {
            amountUSD = payAmount / rate;
        } else {
            amountUSD = payAmount;
        }
        List<UserAccountEntity> userAccountOfMerchant = userAccountService.getUserAccountByMasterAccId(merchantEntity.getMaId());
        for (UserAccountEntity each : userAccountOfMerchant) {
            if (each.getCurrencyType() == userAccountEntity.getCurrencyType()) {
                transactionService.transferMoney(userAccountEntity.getAccountNumber(), userAccountEntity.getCurrencyType(), amountUSD, amountKHR, each.getAccountNumber(), each.getCurrencyType());
                isNotComplete = false;
            }
        }
        if (isNotComplete) {
            for (UserAccountEntity each : userAccountOfMerchant) {
                if (each.getCurrencyType() != userAccountEntity.getCurrencyType()) {
                    transactionService.transferMoney(userAccountEntity.getAccountNumber(), userAccountEntity.getCurrencyType(), amountUSD, amountKHR, each.getAccountNumber(), each.getCurrencyType());
                }
            }
        }
    }

    private Map<String, Object> havePromotion(List<PromotionsEntity> promotionsEntityList, CurrencyType receiveAmountCurrency, String amountCurrency, double payAmount, double receivedMoney) {
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
                    return getPromotion(receiveAmountCurrency, each.getPromotionType(), each.getPromotionValueType(), each.getPromotionAmount(), amountCurrency, payAmount, receivedMoney);
                }
            }
        }
        obj.put("isPromotion", false);
        return obj;
    }

    private Map<String, Object> getPromotion(CurrencyType receiveAmountCurrency, String promotionType, String promotionValueType, String promotionAmount, String amountCurrency, double payAmount, double receivedMoney) {

        Map<String, Object> obj = new HashMap<>();
        if (promotionType.contains("discount")) {
            obj = promotionAndAmount(receiveAmountCurrency, promotionValueType, promotionAmount, amountCurrency, payAmount, receivedMoney);
        }
        if (promotionType.contains("cash-back")) {
            obj = promotionAndAmount(receiveAmountCurrency, promotionValueType, promotionAmount, amountCurrency, payAmount, receivedMoney);
        }
        obj.put("promotionType", promotionType);
        obj.put("promotionValueType", promotionValueType);
        obj.put("promotionAmount", promotionAmount);
        obj.put("isPromotion", true);

        return obj;
    }

    private Map<String, Object> promotionAndAmount(CurrencyType receiveAmountCurrency, String promotionValueType, String promotionAmount, String amountCurrency, double payAmount, double receivedMoney) {
        Map<String, Object> obj = new HashMap<>();
        double finalPromotionAmount = 0.0;
        double finalAmount = 0.0;
        double PromotionValue = Double.parseDouble(promotionAmount.replace("%", ".0")) / 100.0;
        double change;

        if (!receiveAmountCurrency.toString().equals(amountCurrency)) {
            if (amountCurrency.equals(CurrencyType.USD.toString())) {
                receivedMoney = receivedMoney / rate;

            } else if (amountCurrency.equals(CurrencyType.KHR.toString())) {
                receivedMoney = receivedMoney * rate;
            }
        }
        switch (promotionValueType) {
            case "percentage":
                finalPromotionAmount = payAmount * PromotionValue;
                finalAmount = payAmount - finalPromotionAmount;
                break;
            case "fixed-amount":
                finalPromotionAmount = Double.parseDouble(promotionAmount);
                finalAmount = payAmount - finalPromotionAmount;
                break;
        }
        change = receivedMoney - finalAmount;
        obj.put("change", change);
        obj.put("finalPromotionAmount", finalPromotionAmount);
        obj.put("finalAmount", finalAmount);
        return obj;
    }

    private boolean isValidReceiveAmount(double amount, double receiveAmount, UserAccountEntity userAccountEntity, String amountCurrency) {

        if (userAccountEntity.getCurrencyType().toString().equals(amountCurrency)) {
            return amount < receiveAmount;
        } else {
            if (amountCurrency.equals(CurrencyType.USD.toString())) {
                receiveAmount = receiveAmount / rate;
                return amount < receiveAmount;
            } else if (amountCurrency.equals(CurrencyType.KHR.toString())) {
                receiveAmount = receiveAmount * rate;
                return amount < receiveAmount;
            }
        }
        return false;
    }

}
