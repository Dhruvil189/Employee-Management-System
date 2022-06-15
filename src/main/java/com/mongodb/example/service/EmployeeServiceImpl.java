package com.mongodb.example.service;


import com.mongodb.example.exception.InvalidIDException;
import com.mongodb.example.models.Employee;
import com.mongodb.example.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        boolean exists = employeeRepository.existsById(id);
        if (exists) {
            employeeRepository.save(employee);
        } else {
            throw new InvalidIDException("id is invalid");
        }
    }

    @Override
    public Employee delete(int id) {
        boolean exists = employeeRepository.existsById(id);
        if (exists) {
            employeeRepository.deleteById(id);
        } else {
            throw new InvalidIDException("id is invalid");
        }
        return null;
    }


    @Override
    public Employee getEmployee(int id) {
        boolean exists = employeeRepository.existsById(id);

        if (exists) {
            return employeeRepository.findById(id).get();
        } else {
            throw new InvalidIDException("id is invalid");
        }
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findNameAndExcludeId(){
        return employeeRepository.findNameAndExcludeId();
    }

}
