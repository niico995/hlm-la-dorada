package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO {

    private Long employeeID;

    private String name;

    private String lastName;

    private String email;

    private WorkPosition workPosition;

    private Set<Sales> sales = new HashSet<>();

    public EmployeeDTO(Employee employee) {
        this.employeeID = employee.getEmployeeID();
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.workPosition = employee.getWorkPosition();
        this.sales = employee.getSales();
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public WorkPosition getWorkPosition() {
        return workPosition;
    }

    public Set<Sales> getSales() {
        return sales;
    }
}
