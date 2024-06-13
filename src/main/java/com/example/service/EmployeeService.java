package com.example.service;

import com.example.model.Employee;
import graphql.ExecutionResult;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployee();

    ExecutionResult getAll(String query);

    ExecutionResult findByEmail(String query);
}
