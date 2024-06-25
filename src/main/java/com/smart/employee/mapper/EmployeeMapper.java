package com.smart.employee.mapper;

import com.smart.employee.dto.EmployeeDto;
import com.smart.employee.entity.Employee;

public class EmployeeMapper {


    public static EmployeeDto mapToEmployeeDto(Employee employee)
    {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getMobNum()
        );
    }

    public  static Employee mapToEmployee(EmployeeDto employeeDto)
    {
        return  new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getMobNum()
        );
    }
}
