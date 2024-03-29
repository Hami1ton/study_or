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

    public String getDayOfWeekJa() {
        switch (this.dayOfWeek) {
            case MONDAY:
                return "月";
            case TUESDAY:
                return "火";
            case WEDNESDAY:
                return "水";
            case THURSDAY:
                return "木";
            case FRIDAY:
                return "金";
            case SATURDAY:
                return "土";
            case SUNDAY:
                return "日";
            default:
                return "";
        }
   }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + date;
    }

}
