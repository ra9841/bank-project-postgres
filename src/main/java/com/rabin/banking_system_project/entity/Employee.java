package com.rabin.banking_system_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="employee_tbl")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;
    @Column(name = "employee_name", nullable = false)
    private String name;
    @Column(name = "employee_username", nullable = false)
    private String username;
    @Column(name = "employee_email", nullable = false)
    private String email;
    @Column(name = "employee_password", nullable = false)
    private String password;
    @Column(name = "employee_address", nullable = false)
    private String address;
    @Column(name = "employee_role", nullable = false)
    private String role;
    @Column(name = "employee_createDate", nullable = false)
    private Date createDate;
    @Column(name = "employee_modifiedDate", nullable = false)
    private Date modifiedDate;
    @Column(name = "employee_phoneNumber", nullable = false)
    private String phoneNumber;
}
