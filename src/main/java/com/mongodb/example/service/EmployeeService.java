package com.mongodb.example.service;


import com.mongodb.example.models.Employee;
import java.util.List;

public interface EmployeeService {
    public Employee createEmployee(Employee employee);

    public void updateEmployee(int id, Employee employee);

    public Employee delete(int id);

    public Employee getEmployee(int id);

    public List<Employee> getEmployees();

    public List<Employee> findNameAndExcludeId();
}
