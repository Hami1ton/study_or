package org.example.study.or.solver.shiftscheduler;

import java.time.DayOfWeek;
import org.example.study.or.solver.shiftscheduler.models.Shift;
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
                .forEachUniquePair(Shift.class,
                    Joiners.equal(Shift::getEmployee),
                    Joiners.equal(shift -> shift.getBusinessDay().getDate()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("同一営業日に同一社員は1回のみカウントできる");
    }

    private Constraint lessThanTwoEmployees(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Shift.class)
                .groupBy(Shift::getEmployee, count())
                .filter((employee, employeeCount) -> employeeCount != 2)    
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("同一営業日に社員は二人出社しなければならない");
    }

    private Constraint penalizeFShift(ConstraintFactory factory) {
        return factory.forEach(Shift.class)
                .filter(shift -> shift.getEmployee().getName().equals("F"))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Fさんは足を怪我しているため、出社不可");
    }

    private Constraint rewordAShift(ConstraintFactory factory) {
        return factory.forEach(Shift.class)
                .filter(shift -> shift.getEmployee().getName().equals("A")
                    && (
                        shift.getBusinessDay().getDayOfWeek().equals(DayOfWeek.MONDAY)
                        || shift.getBusinessDay().getDayOfWeek().equals(DayOfWeek.TUESDAY)
                        || shift.getBusinessDay().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)
                ))
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Aさんは子供の学校への送迎のため、月火水がいい");
    }

    private Constraint rewordDShift(ConstraintFactory factory) {
        return factory.forEach(Shift.class)
                .filter(shift -> shift.getEmployee().getName().equals("D")
                    && shift.getBusinessDay().getDayOfWeek().equals(DayOfWeek.FRIDAY))
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Dさんは仕事の後飲みに行きたいので金曜日がいい");
    }

}
