package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long empId;
    @Column(name = "FirstName", nullable = false)
    @org.springframework.lang.NonNull
    private String firstName;
    @Column(name = "LastName", nullable = false)
    @JsonProperty(required = true)
    private String lastName;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "PhoneNumber", nullable = false)
    private Long phoneNumber;
    @Column(name = "DOJ", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate doj;
    @Column(name = "SalaryPerMonth", nullable = false)
    private Double salaryPerMonth;

    @Transient
    private Double yearlyAmount;
    @Transient
    private Double taxAmount;
    @Transient
    private Double cessAmount;
}

