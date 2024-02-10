package org.example.study.or.solver.shiftscheduler.models;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;


@PlanningEntity
public class Shift {

    @PlanningId
    private Long id;

    @PlanningVariable
    private BusinessDay businessDay;

    @PlanningVariable
    private Employee employee;


    public Shift() {

    }

    public Shift(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BusinessDay getBusinessDay() {
        return businessDay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setBusinessDay(BusinessDay businessDay) {
        this.businessDay = businessDay;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return "Shift(" + id + ")"; 
    }

}
