package com.rabin.banking_system_project.service;

import com.rabin.banking_system_project.dto.EmployeeDto;
import com.rabin.banking_system_project.entity.Employee;
import com.rabin.banking_system_project.exception.CustomerAlreadyExistException;
import com.rabin.banking_system_project.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto savingOfEmployeeRecord(EmployeeDto employeeDto) {
        Optional<Employee> existEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (existEmployee.isPresent()) {
            log.error("Employee record present in database {}", existEmployee);
            throw new CustomerAlreadyExistException("Employee detail is already present in record");
        }
        Employee employee = new Employee();

        employee.setName(employeeDto.getName().toLowerCase());
        employee.setUsername(employeeDto.getUsername().toLowerCase());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setAddress(employeeDto.getAddress().toLowerCase());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setCreateDate(new Date());
        employee.setModifiedDate(new Date());
        employee.setRole(employeeDto.getRole().toLowerCase());
        employee.setEmail(employeeDto.getEmail().toLowerCase());
        log.info("Employee record saved in database {}", employee);
        Employee employee1 = employeeRepository.save(employee);

        EmployeeDto employeeDto1 = new EmployeeDto();
        BeanUtils.copyProperties(employee1, employeeDto1);
        log.info("Employee record sending to controller {}", employeeDto1);
        return employeeDto1;
    }
}
