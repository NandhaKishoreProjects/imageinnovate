package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.lang.NonNull;
import jakarta.validation.*;

import java.util.Date;

@Data
public class EmployeeModel {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank @Email
    private String email;
    @NotNull @Digits(integer = 10, fraction = 0)
    private Long phoneNumber;
    @NotNull
    private java.time.LocalDate doj;
    @NotNull
    private Double salaryPerMonth;
}
