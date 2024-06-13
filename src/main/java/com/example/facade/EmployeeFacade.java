package com.example.facade;

import com.example.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeFacade {

    public List<Employee> fetchEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper
                    .readValue(new File("employees.json"), new TypeReference<List<Employee>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee findEmployeeByEmail(String email){
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Employee> employees = mapper.readValue(new File("employees.json"), new TypeReference<List<Employee>>() {
            });
            return employees.stream().filter(employee -> email.equals(employee.getEmail()))
                    .collect(Collectors.toList()).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
