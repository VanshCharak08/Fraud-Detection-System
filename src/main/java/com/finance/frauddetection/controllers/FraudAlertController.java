package com.finance.frauddetection.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {

    @GetMapping
    public String getAll(){
        return "All Fraud Alerts";
    }
    @GetMapping("/open")
    public String getOpenAlerts(){
        return "OPen Alerts";
    }
    @PutMapping("/{id}/Status")b
    public String updateStatus(@PathVariable("id") int id){
        return "Status Updation";
    }
}
