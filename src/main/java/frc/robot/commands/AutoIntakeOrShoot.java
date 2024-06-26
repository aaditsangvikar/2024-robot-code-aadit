package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOrShoot extends Command {
    public IndexerSubsystem indexerSubsystem;
    public IntakeSubsystem intakeSubsystem;

    private final Goal goal;


    public AutoIntakeOrShoot(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem, Goal goal) {
        this.intakeSubsystem = intakeSubsystem;
        this.indexerSubsystem = indexerSubsystem;
        this.goal = goal;
        addRequirements(indexerSubsystem, intakeSubsystem);
    }

    @Override
    public void execute() {
        switch (goal) {
            case INTAKE -> {
                if (!indexerSubsystem.isUp()) {
                    intakeSubsystem.setTopSpeed(0.5);
                    intakeSubsystem.setBottomSpeed(0.5);
                    indexerSubsystem.rotateAllWheelsPercent(0.3);
                } else if (indexerSubsystem.isUp()) {
                    // intakeSubsystem.setSpeed(0);
                        indexerSubsystem.rotateAllWheelsPercent(0);
                        intakeSubsystem.setSpeed(0.0);
                }
            }
            case SHOOT -> {
                indexerSubsystem.rotateAllWheelsPercent(1.0);

            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setSpeed(0);
        indexerSubsystem.rotateAllWheelsPercent(0);
    }

    @Override
    public boolean isFinished() {
        System.out.println(this.goal.equals(Goal.INTAKE) && indexerSubsystem.isUp());
        return this.goal.equals(Goal.INTAKE) && indexerSubsystem.isUp();
    }

    public enum Goal {
        INTAKE,
        SHOOT
    }
}
