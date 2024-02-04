package org.example.study.or.solver.shiftscheduler;

import java.time.DayOfWeek;
import org.example.study.or.solver.shiftscheduler.models.Attendance;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;
import static ai.timefold.solver.core.api.score.stream.ConstraintCollectors.*;

public class ShiftScheduleConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            // Hard constraints
            employeeConflict(constraintFactory),
            lessThanTwoEmployees(constraintFactory),
            penalizeFShift(constraintFactory),
            // Soft constraints
            rewordAShift(constraintFactory),
            rewordDShift(constraintFactory),
        };
    }

    private Constraint employeeConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Attendance.class,
                    Joiners.equal(Attendance::getEmployee),
                    Joiners.equal(attendance -> attendance.getBusinessDay().getDate()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("同一営業日に同一社員は1回のみカウントできる");
    }

    private Constraint lessThanTwoEmployees(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Attendance.class)
                .groupBy(Attendance::getEmployee, count())
                .filter((employee, employeeCount) -> employeeCount < 2)    
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("同一営業日に社員は二人出社しなければならない");
    }

    private Constraint penalizeFShift(ConstraintFactory factory) {
        return factory.forEach(Attendance.class)
                .filter(attendance -> attendance.getEmployee().getName().equals("F"))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Fさんは足を怪我しているため、出社不可");
    }

    private Constraint rewordAShift(ConstraintFactory factory) {
        return factory.forEach(Attendance.class)
                .filter(attendance -> attendance.getEmployee().getName().equals("A")
                    && (
                        attendance.getBusinessDay().getDayOfWeek().equals(DayOfWeek.MONDAY)
                        || attendance.getBusinessDay().getDayOfWeek().equals(DayOfWeek.TUESDAY)
                        || attendance.getBusinessDay().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)
                ))
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Aさんは子供の学校への送迎のため、月火水がいい");
    }

    private Constraint rewordDShift(ConstraintFactory factory) {
        return factory.forEach(Attendance.class)
                .filter(attendance -> attendance.getEmployee().getName().equals("D")
                    && attendance.getBusinessDay().getDayOfWeek().equals(DayOfWeek.FRIDAY))
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Dさんは仕事の後飲みに行きたいので金曜日がいい");
    }

}
