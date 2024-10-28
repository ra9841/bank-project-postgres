package com.rabin.banking_system_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String role;
    private Date createDate;
    private Date modifiedDate;
    private String phoneNumber;
}
