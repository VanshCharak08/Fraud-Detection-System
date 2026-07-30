package com.finance.frauddetection.service;

import com.finance.frauddetection.model.Customer;
import com.finance.frauddetection.model.Transaction;
import com.finance.frauddetection.repository.CustomerRepository;
import com.finance.frauddetection.repository.iTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {

    private final iTransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public FraudDetectionService(iTransactionRepository transactionRepository,
                                 CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Get all transactions from the repository
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Get a transaction by its ID
     */
    public Transaction getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    /**
     * Process a transaction and run fraud detection rules
     */
    public Transaction processTransaction(Transaction transaction) {
        // Save transaction and set generated ID
        int newGeneratedIdTxn = transactionRepository.save(transaction);
        transaction.setId(newGeneratedIdTxn);

        List<String> reasons = new ArrayList<>();
        int riskScore = 0;

        // 1) Rule - High Amount
        if (transaction.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            reasons.add("High transaction amount Rs. (" + transaction.getAmount() + ")");
            riskScore += 40;
        }

        // 2) Rule - Odd Hours
        int hour = transaction.getTxnTimestamp().getHour();
        if (hour >= 0 && hour < 5) {
            reasons.add("Transaction made during odd hours (" + hour + ":00)");
            riskScore += 20;
        }

        // 3) Rule - Location Mismatch
        Customer customer = customerRepository.getCustomerById(transaction.getCustomerId());
        if (customer != null &&
                customer.getRegisteredLocation() != null &&
                transaction.getLocation() != null &&
                !customer.getRegisteredLocation().equalsIgnoreCase(transaction.getLocation())) {

            reasons.add("Transaction location mismatch: expected " +
                    customer.getRegisteredLocation() + ", got " +
                    transaction.getLocation());
            riskScore += 30;
        }


        return transaction;
    }
}
