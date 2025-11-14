package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.SpindexPos;

public class SpindexerIntakeCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final int direction;

    private SpindexPos[] Disarray = {SpindexPos.INTAKE_ONE, SpindexPos.INTAKE_TWO, SpindexPos.INTAKE_THREE};
    private int NextIDX = 0;

    public SpindexerIntakeCommand(SpindexerSubsystem spindexerSubsytem, int direction) {
        this.spindexerSubsystem = spindexerSubsytem;
        this.direction = direction;
        addRequirements(spindexerSubsytem);
    }

    @Override
    public void initialize() {
        int curIDX = 0;
        if (spindexerSubsystem.getPosition() == SpindexPos.INTAKE_TWO) {
            curIDX = 1;
        } else if (spindexerSubsystem.getPosition() == SpindexPos.INTAKE_THREE) {
            curIDX = 2;
        }
        NextIDX = curIDX + direction;
        if (NextIDX < 0) {
            NextIDX = 2;
        } else if (NextIDX > 2) {
            NextIDX = 0;
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void execute() {
        spindexerSubsystem.spinTo(Disarray[NextIDX]);
    }
}