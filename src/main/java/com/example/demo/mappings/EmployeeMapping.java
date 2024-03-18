package com.example.demo.mappings;

import com.example.demo.entities.Employee;
import com.example.demo.model.EmployeeModel;

public  interface EmployeeMapping {

    static Employee mapToEmployee(EmployeeModel employeeModel) {
        return Employee.builder()
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .doj(employeeModel.getDoj())
                .email(employeeModel.getEmail())
                .phoneNumber(employeeModel.getPhoneNumber())
                .salaryPerMonth(employeeModel.getSalaryPerMonth())
                .build();
    }
}
