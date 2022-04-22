package com.example.master.customer;

import com.example.master.technician.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createTechnician(@RequestBody Customer customer){
        customerService.createTechnician(customer);
    }

    @PostMapping(path = "/update")
    public void updateQueue(@RequestBody Customer customer){
        customerService.updateQueue(customer);
    }
    @GetMapping
    public List<Customer> getAllTechnician(){
        return customerService.getAll();
    }
}
