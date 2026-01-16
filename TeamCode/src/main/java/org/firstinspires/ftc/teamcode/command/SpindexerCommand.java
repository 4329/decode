package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;

import java.util.concurrent.TimeUnit;

public class SpindexerCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final int direction;
    private Deadline graveyard;
    private boolean executed;

    public SpindexerCommand(SpindexerSubsystem spindexerSubsytem, int direction) {

        this.spindexerSubsystem = spindexerSubsytem;
        this.direction = direction;
        addRequirements(spindexerSubsytem);
    }

    @Override
    public void initialize() {
        executed = false;
    }

    @Override
    public boolean isFinished() {
        return graveyard.hasExpired();
    }

    @Override
    public void execute() {
        if (!executed) {
            int murder;
            if (direction < 0) {
                murder = spindexerSubsystem.left();
            }
            else {
                murder = spindexerSubsystem.right();
            } 
            graveyard = new Deadline(murder, TimeUnit.MILLISECONDS);
         executed = true;   
        }
    }
}