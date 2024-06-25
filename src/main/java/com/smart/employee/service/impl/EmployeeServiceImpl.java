package com.smart.employee.service.impl;

import com.smart.employee.dto.EmployeeDto;
import com.smart.employee.entity.Employee;
import com.smart.employee.exception.EmployeeNotFoundException;
import com.smart.employee.mapper.EmployeeMapper;
import com.smart.employee.repository.EmployeeRepository;
import com.smart.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId).
               orElseThrow(()-> new EmployeeNotFoundException("Employee is Not Found with given Id : "+employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees=employeeRepository.findAll();
        return  employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {


       Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new EmployeeNotFoundException(employeeId+ "Employee is not exists ")
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setMobNum(updatedEmployee.getMobNum());
       Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new EmployeeNotFoundException(employeeId+ "Employee is not exists ")
        );
        employeeRepository.deleteById(employeeId);




    }


}





