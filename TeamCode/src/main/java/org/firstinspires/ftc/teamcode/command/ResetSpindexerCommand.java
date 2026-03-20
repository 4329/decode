package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.RobotState;
import org.firstinspires.ftc.teamcode.util.SpindexerMode;

public class ResetSpindexerCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final BlinkinSubsystem blinkinSubsystem;
    private final RobotState robotState;

    public ResetSpindexerCommand(SpindexerSubsystem spindexerSubsystem, ShooterSubsystem shooterSubsystem, BlinkinSubsystem blinkinSubsystem, RobotState robotState) {
        this.spindexerSubsystem = spindexerSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.blinkinSubsystem = blinkinSubsystem;
        this.robotState = robotState;
    }

    @Override
    public void execute() {
        robotState.setSpindexerModes(SpindexerMode.UNKNOWN);
        blinkinSubsystem.changeMode(SpindexerMode.UNKNOWN);

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
