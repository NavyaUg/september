package com.example.september.service;

import com.example.september.entity.Employee;
import com.example.september.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
//        List<Employee> emp = employeeRepository.findAll();
//        for(Employee empl:emp){
//            if(empl.getEmail().equalsIgnoreCase(employee.getEmail())){
//                System.out.println("come inside if");
//                throw new RuntimeException();
//            }
//        }
        System.out.println("Inside Service" + employee);
        Employee emp = employeeRepository.save(employee);
       System.out.println("Returning the service" + emp);
        if(emp != null){
            return emp;
        }
        return employee;
    }


    public List<Employee> getAll() {
        return employeeRepository.findAll();

    }

    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);

    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
