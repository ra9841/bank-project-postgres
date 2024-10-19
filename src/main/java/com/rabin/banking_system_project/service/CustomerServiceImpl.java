package com.rabin.banking_system_project.service;

import com.rabin.banking_system_project.dto.CustomerDto;
import com.rabin.banking_system_project.entity.Customer;
import com.rabin.banking_system_project.exception.CustomerAlreadyExistException;
import com.rabin.banking_system_project.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public CustomerDto savingTheCustomerRecord(CustomerDto customerDto) {
        Optional<Customer> existCustomer = customerRepository.findByEmail(customerDto.getEmail().toLowerCase());
        if (existCustomer.isPresent()) {
            log.error("Email is already exist in database {}" , existCustomer);
            throw new CustomerAlreadyExistException("Customer Record is already exist");
        }
        Customer customer = new Customer();
        customer.setName(customerDto.getName().toLowerCase());
        customer.setAddress(customerDto.getAddress().toLowerCase());
        customer.setEmail(customerDto.getEmail().toLowerCase());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setUsername(customerDto.getUsername().toLowerCase());
        customer.setCreateDate(new Date());
        customer.setModifiedDate(new Date());
            log.info("Record save in database {}", customer);
        Customer customer1 = customerRepository.save(customer);

        CustomerDto customerDto1 = new CustomerDto();
        BeanUtils.copyProperties(customer1, customerDto1);
        log.info("Record is sending to controller {}", customerDto1);
        return customerDto1;
    }

//    @Override
//    public List<CustomerDto> listOfCustomerRecord() {
//        List<Customer> customers = customerRepository.findAll();
//        log.info("Customer record from database {}", customers);
//        List<CustomerDto> customerDto1 = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            CustomerDto customerDto = new CustomerDto();
//            BeanUtils.copyProperties(customer, customerDto);
//            customerDto1.add(customerDto);
//            log.info("Customer record sending to controller {}", customerDto);
//        }
//
//        return customerDto1;
//    }


    //Other way in using stream for get mapping
    @Override
    public List<CustomerDto> listOfCustomerRecord() {
        List<Customer> customers = customerRepository.findAll();
        log.info("Customer record from database {}", customers);
        return customers.stream().map(customer -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            log.info("Customer record sending to controller {}", customerDto);
            return customerDto;
        }).toList();
    }

//    @Override
//    public String deletingTheRecordByEmail(String email) {
//        Optional<Customer> existCustomer = customerRepository.findByEmail(email);
//        log.info("Customer Record found in database {}", existCustomer);
//        if (existCustomer.isPresent()) {
//            customerRepository.delete(existCustomer.get());
//            return "delete successfully";
//        }
//        return "not deleted.....no record found";
//    }

    //other way for delete mapping
    @Override
    public String deletingTheRecordByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return "delete successfully";
                })
                .orElse("not deleted.....no record found");
    }


}
