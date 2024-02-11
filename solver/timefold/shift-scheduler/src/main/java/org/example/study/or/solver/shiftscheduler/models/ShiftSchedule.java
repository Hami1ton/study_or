package org.example.study.or.solver.shiftscheduler.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.List;
import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;


@PlanningSolution
public class ShiftSchedule {

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<BusinessDay> businessDays;

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<Employee> employees;

    @PlanningEntityCollectionProperty
    private List<Shift> shifts;

    @PlanningScore
    private HardSoftScore score;

    public ShiftSchedule(){

    }

    public ShiftSchedule(List<BusinessDay> businessDays, List<Employee> employees, List<Shift> shifts) {
        this.businessDays = businessDays;
        this.employees = employees;
        this.shifts = shifts;    
    }

    public List<BusinessDay> getBusinessDays() {
        return businessDays;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void exportShifts() {
        File file = new File("public/ShiftSchedule.csv");
        
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            // 日付・名前の昇順にソート
            List<Shift> sorted_shifts = shifts.stream()
                .sorted(Comparator.comparing((Shift shift) -> shift.getBusinessDay().getDate())
                    .thenComparing((Shift shift) -> shift.getEmployee().getName())
                )
                .toList();

            for(var shift : sorted_shifts) {
                bw.write(shift.getBusinessDay().getDate() + "," + shift.getBusinessDay().getDayOfWeekJa() + "," + shift.getEmployee().getName());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
