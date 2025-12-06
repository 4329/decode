package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;

public class SpindexerCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final int direction;


    public SpindexerCommand(SpindexerSubsystem spindexerSubsytem, int direction) {

        this.spindexerSubsystem = spindexerSubsytem;
        this.direction = direction;
        addRequirements(spindexerSubsytem);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void execute() {
        if (direction < 0) {
            spindexerSubsystem.left();
        }
        else {
            spindexerSubsystem.right();
        }
    }
}