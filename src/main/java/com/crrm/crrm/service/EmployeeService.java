package com.crrm.crrm.service;

import com.crrm.crrm.entity.Employee;
import com.crrm.crrm.exception.ResourceNotFoundException;
import com.crrm.crrm.payload.EmployeeDto;
import com.crrm.crrm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);

        return employeeDto;


    }


    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updatedEmployee);
        return employeeDto;

    }

    public List<EmployeeDto> getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest page = PageRequest.of(pageSize, pageNo, sort);


        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employee = all.getContent();

        List<EmployeeDto> dto = employee.stream()
                .map(e -> mapToDto(e)).collect(Collectors.toList());

        return dto;
    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
        return dto;

    }

    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;


    }

    public EmployeeDto getEmployeesById(long empId) {

        Employee employee=employeeRepository.
                findById(empId).orElseThrow(()->new
                        ResourceNotFoundException("not found id : "+empId));

        EmployeeDto dto = mapToDto(employee);

        return dto;


    }
}
