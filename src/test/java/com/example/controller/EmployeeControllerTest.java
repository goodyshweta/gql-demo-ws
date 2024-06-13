package com.example.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    //@Test
    public void getEmployee() {
        String s="test";
        Assert.assertEquals("Employee fecthed from DB",employeeController.getEmployee());
    }
}