package com.example.september.controller;

// we need to write the Rest API's - representational state transfer - CRUD operation, create, read, update and delete
// post,get,put,delete

import com.example.september.entity.Employee;
import com.example.september.exception.EmployeeNotFoundException;
import com.example.september.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired // used for Injecting
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        System.out.println("Inside Controller" + employee);
        Employee emp = employeeService.save(employee);
        return emp;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {

        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long Id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeService.getById(Id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(updatedEmployee.getName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setId(updatedEmployee.getId());
            employee.setEmail(updatedEmployee.getEmail());
            employeeService.save(employee);
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
        if(!employeeService.getById(id).isPresent()){
            throw new EmployeeNotFoundException(id);
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

}
