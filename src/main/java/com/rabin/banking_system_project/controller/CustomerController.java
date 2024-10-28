package com.rabin.banking_system_project.controller;

import com.rabin.banking_system_project.dto.CustomerDto;
import com.rabin.banking_system_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
//before running application run your redis server.Until & unless you do not close your redis server, data were
// saved in redis server otherwise put time limit on properties file.(redis-server) on cmd and then on another cmd(redis-cli)then type (monitor) for monitor
@EnableCaching
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping //localhost:8080/customers
    public ResponseEntity<CustomerDto> registrationOfCustomerRecord(@RequestBody CustomerDto customerDto){
     return ResponseEntity.ok(customerService.savingTheCustomerRecord(customerDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> gettingListOfRecord() {
        return ResponseEntity.ok(customerService.listOfCustomerRecord());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deletingTheRecord(@PathVariable String email){
      return ResponseEntity.ok(customerService.deletingTheRecordByEmail(email));
    }

    //for update operation first we need to retrieve particular data
    @GetMapping("/shows/{email}")
    public ResponseEntity<CustomerDto> gettingParticularRecord(@PathVariable String email){
     return ResponseEntity.ok( customerService.getParticularCustomerRecord(email));
    }

    //now we are retrieving the record to update
    @PutMapping("/{email}")
    public ResponseEntity<CustomerDto> updatingTheRecord(@RequestBody CustomerDto customerDto,@PathVariable String email){
     return ResponseEntity.ok(customerService.updatingTheCustomerRecord(customerDto,email));
    }


}
