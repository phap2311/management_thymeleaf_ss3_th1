package com.example.customer_management_thymeleaf.service;

import com.example.customer_management_thymeleaf.model.Customer;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.processor.SpringErrorClassTagProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class CustomerService implements ICustomerService{
    private static Map<Integer,Customer> customers;
    static {
        customers = new HashMap<>();
        customers.put(1,new Customer(1,"van nhat","nhat@kk","quang tri"));
        customers.put(2,new Customer(2,"van dat","dat@kk","quang binh"));
        customers.put(3,new Customer(3,"van dung","dung@kk","quang nam"));
        customers.put(4,new Customer(4,"van phap","phap@kk","quang ninh"));
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer findByid(int id) {
        return customers.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customers.put(id,customer);
    }

    @Override
    public void remove(int id) {
        customers.remove(id);
    }

}
