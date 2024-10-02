package com.example.september.controllerTest;

import com.example.september.controller.EmployeeController;
import com.example.september.entity.Employee;
import com.example.september.service.EmployeeService;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createEmployeeTest() {
        Employee empmock = new Employee();
        empmock.setEmail("navya@gmail.com");
        empmock.setId(1L);
        empmock.setName("navya");
        empmock.setDepartment("IT");
        Employee employee=employeeController.createEmployee(empmock);

        Mockito.when(employeeService.save(empmock)).thenReturn(empmock);
        //Employee employee=employeeController.createEmployee(empmock);
        Assertions.assertEquals(empmock,employee);
        Assertions.assertNotNull(employee);
    }

    @Test
    public void getAllEmployeeTest(){
        List<Employee> employeeList = new ArrayList<>();
        Employee e = new Employee();
        e.setEmail("navya@gmail.com");
        e.setId(1L);
        e.setName("navya");
        e.setDepartment("IT");
        Employee e1 = new Employee();
        e1.setEmail("ram@gmail.com");
        e1.setDepartment("cse");
        e1.setName("rams");
        e1.setId(2L);
        employeeList.add(e);
        employeeList.add(e1);
        List<Employee> employeeLis = Arrays.asList(e,e1);
        Mockito.when(employeeService.getAll()).thenReturn(employeeLis);
        ResponseEntity<List<Employee>> empl=employeeController.getAllEmployee();
        Assertions.assertEquals(empl.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee e = new Employee();
        e.setId(1L);
        Mockito.when(employeeService.getById(1L)).thenReturn(Optional.of(e));
        Optional<Employee> e212 = employeeController.getEmployeeById(1L);
        Assertions.assertEquals(e212.get().getId(), e.getId());
    }

    @Test
    public void updateEmployeeTest(){
        Employee e = new Employee();
        e.setName("uma");
        e.setEmail("navya@gmail.com");
        e.setId(2L);
        Mockito.when(employeeService.getById(1L)).thenReturn(Optional.of(e));
        Employee result = employeeController.updateEmployee(1L, e).getBody();
        Assertions.assertEquals(e.getId(),result.getId());
    }

    @Test
    public void testDeleteEmployee(){
        Employee e = new Employee();
        e.setName("uma");
        e.setId(1L);
        e.setEmail("navya@gmail.com");
        Mockito.when(employeeService.deleteEmployee(1L));
    }

}
