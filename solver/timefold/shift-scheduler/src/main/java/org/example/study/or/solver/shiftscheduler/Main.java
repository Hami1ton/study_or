package org.example.study.or.solver.shiftscheduler;

import org.example.study.or.solver.shiftscheduler.models.Shift;
import org.example.study.or.solver.shiftscheduler.models.ShiftSchedule;
import java.time.Duration;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;

public class Main {
    
    public static void main(String[] args) {
        SolverFactory<ShiftSchedule> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(ShiftSchedule.class)
                .withEntityClasses(Shift.class)
                .withConstraintProviderClass(ShiftScheduleConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        // Load Demo Data
        ShiftSchedule problem = DemoDataFactory.createDemoData();

        // Solve the problem
        Solver<ShiftSchedule> solver = solverFactory.buildSolver();
        ShiftSchedule solution = solver.solve(problem);

        solution.exportShifts();

    }
}
