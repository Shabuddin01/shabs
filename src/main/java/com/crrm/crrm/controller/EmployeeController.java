package com.crrm.crrm.controller;
import java.util.Optional;
import com.crrm.crrm.entity.Employee;
import com.crrm.crrm.exception.ResourceNotFoundException;
import com.crrm.crrm.payload.EmployeeDto;
import com.crrm.crrm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto, BindingResult result) {

        EmployeeDto employeeDto = employeeService.addEmployee(dto);

        if (result.hasErrors()) {
return new ResponseEntity<>(result.getFieldError()
        .getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);        }
            return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {

        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam Long id,
                                                      @RequestBody EmployeeDto dto){
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);

        return new ResponseEntity<>( employeeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(name="PageNo", required = false, defaultValue = "0")int pageNo,
            @RequestParam(name="PageSize", required = false, defaultValue = "2")int pageSize,
            @RequestParam(name="sortBy", required = false, defaultValue = "name")String sortBy,
            @RequestParam(name="sortDir", required = false, defaultValue="asc")String sortDir
    ){
       List<EmployeeDto> employees =  employeeService.getEmployees(pageSize, pageNo, sortBy, sortDir);
       return new ResponseEntity<>( employees, HttpStatus.OK);
    }

    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeesById(
            @PathVariable long empId){

        EmployeeDto dto = employeeService.getEmployeesById(empId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
