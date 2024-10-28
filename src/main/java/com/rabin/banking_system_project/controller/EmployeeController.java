package com.rabin.banking_system_project.controller;

import com.rabin.banking_system_project.dto.EmployeeDto;
import com.rabin.banking_system_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> registrationOfEmployeeRecord(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.savingOfEmployeeRecord(employeeDto));
    }
}
