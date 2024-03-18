package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class FirstController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    Validator validator;

    @GetMapping
    public String helloString() throws Exception {
        return "hello i am from " + Inet4Address.getLocalHost().getHostAddress();
    }

    @GetMapping(path = "/findAllEmployees")
    public ResponseEntity<ApiResponse<List<Employee>, HttpStatus>> getAllEmployees() {

        return new ResponseEntity<ApiResponse<List<Employee>, HttpStatus>>(
                ApiResponse.<List<Employee>, HttpStatus>builder().data(employeeService.getAllEmployees()).build(),
                HttpStatusCode.valueOf(200));
    }

    @PostMapping(path = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String, List<String>>> insertEmployee(@RequestBody EmployeeModel employee) {
        String status = "Success";
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employee);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream().map(x -> x.getMessage()).collect(Collectors.toList());
            // Here you can use the values taken from violations according to your needs.
            return new ResponseEntity<>(ApiResponse.<String, List<String>>builder().errors(errorMessages).build(), HttpStatus.BAD_REQUEST);
        }
        try {
            employeeService.saveEmployee(employee);
        }catch (Exception ex){
            status = "Failed";
            ex.printStackTrace();
        }
        return new ResponseEntity<ApiResponse<String, List<String>>>(ApiResponse.<String, List<String>>builder().data(status).build(), HttpStatusCode.valueOf(200));
    }

}
