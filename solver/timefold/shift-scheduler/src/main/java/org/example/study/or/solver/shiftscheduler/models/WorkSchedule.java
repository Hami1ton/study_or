package org.example.study.or.solver.shiftscheduler.models;

import java.util.List;

import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class WorkSchedule {
    
    @ProblemFactCollectionProperty
    private List<BusinessDay> businessDays;

    @ProblemFactCollectionProperty
    private List<Employee> employees;

    @ProblemFactCollectionProperty
    private List<Attendance> attendances;

    @PlanningScore
    private HardSoftScore score;

    public WorkSchedule(){

    }

    public WorkSchedule(List<BusinessDay> businessDays, List<Employee> employees, List<Attendance> attendances) {
        this.businessDays = businessDays;
        this.employees = employees;
        this.attendances = attendances;    
    }

    public List<BusinessDay> getBusinessDays() {
        return businessDays;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Attendance> gAttendances() {
        return attendances;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
