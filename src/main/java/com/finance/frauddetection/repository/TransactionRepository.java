package com.finance.frauddetection.repository;

import com.finance.frauddetection.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransactionRepository implements iTransactionRepository {
    private List <Transaction> transactions = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public List<Transaction> findAll(){
        return transactions;
    }

    @Override
    public Transaction findById(int id){
        for(Transaction transaction : transactions){
            if(transaction.getId()==id){
                return transaction;
            }
        }
        return null;
    }
    @Override
    public int save(Transaction transaction) {
        int newId = idCounter.getAndIncrement();
        transaction.setId(newId);
        transactions.add(transaction); //insert
        return transaction.getId();
    }
    @Override
    public void updateStatus(int id, String status){
        Transaction txn = findById((id));
        if(txn!=null){
            txn.setStatus(status);
        }
    }
}
