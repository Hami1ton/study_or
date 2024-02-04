package org.example.study.or.solver.shiftscheduler.models;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BusinessDay {
    
    private DayOfWeek dayOfWeek;

    private LocalDate date;

    public BusinessDay() {

    }

    public BusinessDay(DayOfWeek dayOfWeek, LocalDate date) {
        this.dayOfWeek = dayOfWeek;
        this.date = date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + date;
    }

}
