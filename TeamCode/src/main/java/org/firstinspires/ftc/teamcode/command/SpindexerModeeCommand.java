package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.RobotState;
import org.firstinspires.ftc.teamcode.util.SpindexerMode;

public class SpindexerModeeCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final BlinkinSubsystem blinkinSubsystem;
    private final RobotState robotState;

    public SpindexerModeeCommand(SpindexerSubsystem spindexerSubsystem, ShooterSubsystem shooterSubsystem, BlinkinSubsystem blinkinSubsystem, RobotState robotState) {
        this.spindexerSubsystem = spindexerSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.blinkinSubsystem = blinkinSubsystem;
        this.robotState = robotState;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void execute() {
        SpindexerMode currentMode = robotState.getSpindexerModes();
        SpindexerMode newMode;
        if (SpindexerMode.SHOOT.equals(currentMode)) {
            newMode = SpindexerMode.INTAKE;
        } else {
            newMode = SpindexerMode.SHOOT;
        }
        robotState.setSpindexerModes(newMode);
        blinkinSubsystem.changeMode(newMode);
        spindexerSubsystem.changeMode(newMode);
        shooterSubsystem.changeMode(newMode);
    }
}
