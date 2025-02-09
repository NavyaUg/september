package com.example.september.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // mvn dependency:tree

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // To increment the id
    private Long id;
    private String name;
    private String department;
    private String email;

    public Employee(){

    }

    public Employee(long id, String name, String department, String email) {
        this.id = id;
        this.name =name;
        this.department = department;
        this.email = email;
    }

    @Override
    public String toString(){
        return "Employee{" +
                "id="+ id +
                ",name='" + name + '\''+
                ",department='" + department + '\'' +
                ", email='" +email +'\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
