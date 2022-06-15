package com.mongodb.example.controller;


import com.mongodb.example.dto.EmployeeDto;
import com.mongodb.example.mapper.EmployeeMapper;
import com.mongodb.example.models.Employee;
import com.mongodb.example.models.GroupByRole;
import com.mongodb.example.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/addEmployee")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDto employeedto) {
        logger.info("Execution of adding employees are started");
        Employee employee = mapper.dtoToModel(employeedto);
        Employee newEmployee = employeeService.createEmployee(employee);
        logger.info("Employees are added with name:{}", newEmployee.getName());
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }



    @GetMapping("/getAllEmployees")
    public ResponseEntity<Object> getEmployees() {
        logger.info("Execution of getting all employees are started");
        List<EmployeeDto> employeeList = mapper.modelsToDtos(employeeService.getEmployees());
        logger.info("List of the employees:{}", employeeList.size());
        return new ResponseEntity<>(employeeList, HttpStatus.OK);

    }


    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable("id") int id) {
        logger.info("Execution of searching employee started with id:{}", id);
        EmployeeDto employeedto = mapper.modelToDto(employeeService.getEmployee(id));
        logger.info("Employee has been searched with name:{}", employeedto.getFullName());
        return new ResponseEntity<>(employeedto, HttpStatus.OK);
    }


    @PutMapping("/updatedEmployee/{id}")
    public ResponseEntity<Object> updateEmployee(@Valid @PathVariable("id") int id, @RequestBody EmployeeDto employeedto) {
        logger.info("Execution of updating employee started with id:{}", id);
        Employee employee = mapper.dtoToModel(employeedto);
        employeeService.updateEmployee(id, employee);
        logger.info("Employee details  are updated with name:{}", employee.getName());
        return new ResponseEntity<>("details are updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) {
        logger.info("Execution of deleting employee started with id:{}", id);
        mapper.modelToDto(employeeService.delete(id));
        logger.info("Execution of deleting employee completed");
        return new ResponseEntity<>("details are deleted", HttpStatus.OK);

    }


    @GetMapping("/group")
    public List<GroupByRole> getGroupByRole() {

        Aggregation agg = newAggregation(
                group("employeeRole").count().as("total").sum("salary").as("totalSalary"),
                project("total", "totalSalary").and("employeeRole").previousOperation());
        AggregationResults<GroupByRole> groupResults
                = mongoTemplate.aggregate(agg, Employee.class, GroupByRole.class);
        List<GroupByRole> result = groupResults.getMappedResults();
        return result;
    }


    @GetMapping("/nameExcludeId")
    public ResponseEntity<Object> getNameExcludeId() {
        logger.info("Execution of findNameAndExcludeId started");
        List<EmployeeDto> employeeList = mapper.modelsToDtos(employeeService.findNameAndExcludeId());
        logger.info("List of the employees:{}", employeeList.size());
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


}


