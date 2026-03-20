package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.ArtifactOrder;
import org.firstinspires.ftc.teamcode.util.RobotState;
import org.firstinspires.ftc.teamcode.util.SpindexPos;

import java.util.concurrent.TimeUnit;

public class ObeliskSpinCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final RobotState robotState;
    private Deadline deathToServo;

    public ObeliskSpinCommand(SpindexerSubsystem spindexerSubsystem, RobotState robotState) {
        this.spindexerSubsystem = spindexerSubsystem;
        this.robotState = robotState;
        this.deathToServo = new Deadline(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void initialize() {
        deathToServo.reset();
    }

    @Override
    public void execute() {
        if (ArtifactOrder.GPP.equals(robotState.getArtifactOrder())) {
            spindexerSubsystem.spinTo(SpindexPos.SHOOT_ONE);
        }
        else if (ArtifactOrder.PGP.equals(robotState.getArtifactOrder())) {
            spindexerSubsystem.spinTo(SpindexPos.SHOOT_THREE);
        }
        else if (ArtifactOrder.PPG.equals(robotState.getArtifactOrder())) {
            spindexerSubsystem.spinTo(SpindexPos.SHOOT_TWO);

        }
    }

    @Override
    public boolean isFinished() {
        return deathToServo.hasExpired();
    }
}