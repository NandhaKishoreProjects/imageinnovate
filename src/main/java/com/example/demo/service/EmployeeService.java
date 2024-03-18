package com.example.demo.service;

import com.example.demo.entities.Employee;
import com.example.demo.mappings.EmployeeMapping;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        logger.info("before making call to db find all ");
        List<Employee> emplList =  employeeRepository.findAll();

        if(!CollectionUtils.isEmpty(emplList)) {
            logger.info("size of employees :  "+emplList.size());
           return emplList.stream().map(x -> {

             LocalDate doj =  x.getDoj();
             Double lop = x.getSalaryPerMonth() / 30;
             LocalDate yearEndDate = LocalDate.parse("2024-03-31");

               Period period = Period.between(doj, yearEndDate);
               Double yearlyAmount = period.getMonths() * x.getSalaryPerMonth();
               yearlyAmount = yearlyAmount + (period.getDays() * lop);
               Double taxAmount = 0d;

               if(!(yearlyAmount < 250000)) {
                   double tempSalary = 0d;
                   if(yearlyAmount <= 500000) {
                       tempSalary = yearlyAmount - 250000;
                       taxAmount += calculatePercentage(tempSalary, 5);
                   } else {
                       tempSalary = 250000;
                       taxAmount += calculatePercentage(tempSalary, 5);
                       if( yearlyAmount <= 1000000) {
                           tempSalary = yearlyAmount - 500000;
                           taxAmount += calculatePercentage(tempSalary, 10);
                       } else {
                           tempSalary = 500000;
                           taxAmount += calculatePercentage(tempSalary, 10);

                           if(yearlyAmount > 2500000) {
                               tempSalary = 1500000;
                               taxAmount += calculatePercentage(tempSalary, 20);
                               tempSalary = yearlyAmount - 2500000;
                               double cessAMount = calculatePercentage(tempSalary, 2);
                               taxAmount += cessAMount;
                               x.setCessAmount(cessAMount);
                           } else {
                               tempSalary = yearlyAmount - 1000000;
                               taxAmount += calculatePercentage(tempSalary, 20);
                           }
                       }
                   }

               } else {
                   logger.info("no tax required!");
               }

               x.setTaxAmount(taxAmount);
               x.setYearlyAmount(yearlyAmount);
               return x;
           }).collect(Collectors.toList());
        } else {
            logger.info("no employees found");
        }

       return emplList;
    }

    public double calculatePercentage(double totalSalary, int percentageNumber) {
        return (totalSalary * percentageNumber)/100;
    }

    public Employee saveEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(EmployeeMapping.mapToEmployee(employeeModel));
    }
}
