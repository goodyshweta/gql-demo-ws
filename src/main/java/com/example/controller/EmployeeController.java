package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployee(){
        List<Employee> employees = employeeService.getEmployee();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestBody String query) {
        ExecutionResult result = employeeService.getAll(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping("/getPersonByEmail")
    public ResponseEntity<Object> getPersonByEmail(@RequestBody String query) {
        ExecutionResult result = employeeService.findByEmail(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
