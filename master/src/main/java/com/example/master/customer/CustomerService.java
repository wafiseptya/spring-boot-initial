package com.example.master.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(@Qualifier("postgrescustomer") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public int createTechnician(Customer customer){
        return customerRepository.insertTechnician(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.selectAllCustomer();
    }

    public List<Customer> getAvailable() {
        return customerRepository.selectAvailableCustomer();
    }

    public int updateQueue(UUID uuid, Integer queue){
        return customerRepository.updateQueue(uuid, queue);
    }
}
