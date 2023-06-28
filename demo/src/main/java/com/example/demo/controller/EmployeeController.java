package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    //read
    @GetMapping("/employees")
    public List<Employees> getAllEmployees(){
        return employeeRepository.findAll();
    }
    //create
    @PostMapping("/employees")
    public Employees createEmployee(@RequestBody Employees employees){
        return employeeRepository.save(employees);
    }
    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id){
        Employees employees = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employees);
    }
    //updating employee details
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable Long id,@RequestBody Employees employeeDetails){
        Employees employees = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not exists with id :"+ id));
        employees.setFirstname(employeeDetails.getFirstname());
        employees.setLastname(employeeDetails.getLastname());
        employees.setEmail(employeeDetails.getEmail());

        Employees updatedEmployee = employeeRepository.save(employees);
        return ResponseEntity.ok(updatedEmployee);

    }
    //delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Object>> deleteEmployee(@PathVariable Long id){
        Employees employees = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exists with id :" + id));
        employeeRepository.delete(employees);
        Map<String, Object> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
