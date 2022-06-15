package com.mongodb.example.dto;

import com.mongodb.example.models.EmployeeRole;
import com.mongodb.example.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {

    private int id;
    @NotEmpty
    @Size(min = 2, message = "Employee name should have atleast 2 characters")
    private String fullName;
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
