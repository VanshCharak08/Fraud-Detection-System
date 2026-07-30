package com.finance.frauddetection.controllers;

import com.finance.frauddetection.model.Transaction;
import com.finance.frauddetection.service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private FraudDetectionService fraudDetectionService;
    public TransactionController(FraudDetectionService fraudDetectionService){
        this.fraudDetectionService = fraudDetectionService;
    }
//    @GetMapping("/transactions")

    @GetMapping
    public List<Transaction> getAll(){
        return fraudDetectionService.getAllTransactions();
    }
//    @GetMapping("transactions/buy")
//    public String buyAll(){
//        return "Buyed Chocolates";
//    }
    @GetMapping("/{id}")
    public String getById(@PathVariable int id){
        return "Get transaction by id : " + id;
    }

    @PostMapping
    public List<Transaction> getTransactions(){
        return fraudDetectionService.getAllTransactions();
    }

}
