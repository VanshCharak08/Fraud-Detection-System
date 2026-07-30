package com.finance.frauddetection.repository;

import com.finance.frauddetection.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();
    public CustomerRepository(){
        customers.add(new Customer(1,"Vansh","ABC12122","India"));
        customers.add(new Customer(2,"nsh","ABC12132","UK"));
        customers.add(new Customer(3,"Ansh","ABC10122","US"));
    }

    public List<Customer> getCustomers(){
        return customers;
    }
    public Customer getCustomerById(int id){
        return customers.stream().filter(customer -> customer.getId()).findFirst().orElse(null);
    }
}
