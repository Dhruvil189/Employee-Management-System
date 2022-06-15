package com.mongodb.example.repository;

import com.mongodb.example.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
    @Query(value = "{}", fields = "{name : 1,_id : 0,salary :1}")
    public List<Employee> findNameAndExcludeId();
}
