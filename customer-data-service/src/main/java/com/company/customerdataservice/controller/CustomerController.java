package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @PostMapping("/Customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return repo.save(customer);
    }
    @PutMapping("/Customer")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer updateCustomer(@RequestBody Customer customer){
        return repo.save(customer);
    }

    @DeleteMapping("/Customer/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable int id){
       repo.deleteById(id);
    }

   @GetMapping("/Customer/{id}")
   @ResponseStatus(value = HttpStatus.OK)
    public Optional<Customer> getCustomer(@PathVariable int id){
        return  repo.findById(id);
    }

    @GetMapping("/Customer/getByState/{state}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getByState(@PathVariable String state){
        return repo.findByState(state);
    }

}
