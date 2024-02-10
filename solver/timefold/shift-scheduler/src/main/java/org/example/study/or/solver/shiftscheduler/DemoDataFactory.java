package org.example.study.or.solver.shiftscheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.study.or.solver.shiftscheduler.models.BusinessDay;
import org.example.study.or.solver.shiftscheduler.models.Employee;
import org.example.study.or.solver.shiftscheduler.models.Shift;
import org.example.study.or.solver.shiftscheduler.models.ShiftSchedule;


public class DemoDataFactory {
    

    public static ShiftSchedule createDemoData() {
        List<Employee> employees = createEmployees();
        List<BusinessDay> businessDays = createBusinessDays();

        // 1営業日に二人社員が出社するので、営業日×2分のShiftを作成
        List<Shift> shifts = new ArrayList<>();
        long nextId = 0L;
        for(int i = 0; i < businessDays.size() * 2; i++) {
            shifts.add(new Shift(nextId++));
        }

        return new ShiftSchedule(businessDays, employees, shifts);
    }

    private static List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("A"));
        employees.add(new Employee("B"));
        employees.add(new Employee("C"));
        employees.add(new Employee("D"));
        employees.add(new Employee("E"));
        employees.add(new Employee("F"));
        employees.add(new Employee("G"));
        employees.add(new Employee("H"));
        employees.add(new Employee("I"));
        employees.add(new Employee("J"));
        employees.add(new Employee("K"));
        employees.add(new Employee("L"));
        employees.add(new Employee("M"));
        employees.add(new Employee("N"));
        employees.add(new Employee("O"));
        employees.add(new Employee("P"));

        return employees;
    }

    private static List<BusinessDay> createBusinessDays() {
        List<BusinessDay> businessDays = new ArrayList<>();
        businessDays.add(new BusinessDay(DayOfWeek.THURSDAY, LocalDate.of(2024, 2, 1)));
        businessDays.add(new BusinessDay(DayOfWeek.FRIDAY, LocalDate.of(2024, 2, 2)));
        businessDays.add(new BusinessDay(DayOfWeek.MONDAY, LocalDate.of(2024, 2, 5)));
        businessDays.add(new BusinessDay(DayOfWeek.TUESDAY, LocalDate.of(2024, 2, 6)));
        businessDays.add(new BusinessDay(DayOfWeek.WEDNESDAY, LocalDate.of(2024, 2, 7)));
        businessDays.add(new BusinessDay(DayOfWeek.THURSDAY, LocalDate.of(2024, 2, 8)));
        businessDays.add(new BusinessDay(DayOfWeek.FRIDAY, LocalDate.of(2024, 2, 9)));
        businessDays.add(new BusinessDay(DayOfWeek.TUESDAY, LocalDate.of(2024, 2, 13)));
        businessDays.add(new BusinessDay(DayOfWeek.WEDNESDAY, LocalDate.of(2024, 2, 14)));
        businessDays.add(new BusinessDay(DayOfWeek.THURSDAY, LocalDate.of(2024, 2, 15)));
        businessDays.add(new BusinessDay(DayOfWeek.FRIDAY, LocalDate.of(2024, 2, 16)));
        businessDays.add(new BusinessDay(DayOfWeek.MONDAY, LocalDate.of(2024, 2, 19)));
        businessDays.add(new BusinessDay(DayOfWeek.TUESDAY, LocalDate.of(2024, 2, 20)));
        businessDays.add(new BusinessDay(DayOfWeek.WEDNESDAY, LocalDate.of(2024, 2, 21)));
        businessDays.add(new BusinessDay(DayOfWeek.THURSDAY, LocalDate.of(2024, 2, 22)));
        businessDays.add(new BusinessDay(DayOfWeek.MONDAY, LocalDate.of(2024, 2, 26)));
        businessDays.add(new BusinessDay(DayOfWeek.TUESDAY, LocalDate.of(2024, 2, 27)));
        businessDays.add(new BusinessDay(DayOfWeek.WEDNESDAY, LocalDate.of(2024, 2, 28)));
        businessDays.add(new BusinessDay(DayOfWeek.THURSDAY, LocalDate.of(2024, 2, 29)));

        return businessDays;
    }

}
