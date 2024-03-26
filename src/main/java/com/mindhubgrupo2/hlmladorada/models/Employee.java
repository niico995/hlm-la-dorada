package com.mindhubgrupo2.hlmladorada.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private Rol rol;

    private WorkPosition workPosition;

    @OneToMany(mappedBy = "employeeHolder", fetch = FetchType.EAGER)
    private Set<Sales> sales = new HashSet<>();

    public Employee() {};
    public Employee(String name, String lastName, String email, String password, Rol rol, WorkPosition workPosition) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.workPosition = workPosition;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public WorkPosition getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(WorkPosition workPosition) {
        this.workPosition = workPosition;
    }

    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    public void addSale(Sales sale) {
        sale.setEmployeeHolder(this);
        sales.add(sale);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + employeeID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", workPosition=" + workPosition +
                ", sales=" + sales +
                '}';
    }
}