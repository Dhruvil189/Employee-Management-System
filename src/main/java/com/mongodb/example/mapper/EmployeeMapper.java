package com.mongodb.example.mapper;

import com.mongodb.example.dto.EmployeeDto;
import com.mongodb.example.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "employee.name", target = "fullName")
    @Mapping(target = "password", ignore = true)
    EmployeeDto modelToDto(Employee employee);

    @Mapping(source = "employeeDto.fullName", target = "name")
    Employee dtoToModel(EmployeeDto employeeDto);

    List<EmployeeDto> modelsToDtos(List<Employee> employee);
}
