package org.example.study.or.solver.shiftscheduler.models;

public class Employee {

    private String name;

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
