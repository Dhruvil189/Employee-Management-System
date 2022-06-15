package com.mongodb.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupByRole {

    private EmployeeRole  employeeRole;

    private long total;

    private Map<Object,Object> role;

    private double totalSalary;
    private String city;
    private List<Product> products;
}
