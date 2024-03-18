package com.example.demo.repository;

import com.example.demo.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Optional<Employee> findById(Long aLong);

    @Override
    List<Employee> findAll();
}
