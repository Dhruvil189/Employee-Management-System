package com.mongodb.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")

public class Employee {

    private int id;
    @NotEmpty
    @Size(min = 2, message = "Employee name should have atleast 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, message = "City name should have atleast 2 characters")
    private String city;
    private EmployeeRole employeeRole;
    private String password;
    @NotEmpty
    @Size(min=5000)
    private double salary;
    private List<Product> products;

    }


