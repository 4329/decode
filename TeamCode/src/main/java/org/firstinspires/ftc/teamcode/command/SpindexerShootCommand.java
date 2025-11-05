package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.SpindexPos;

import java.security.PrivateKey;

public class SpindexerShootCommand extends CommandBase {
    private final SpindexerSubsystem spindexerSubsystem;
    private final int direction;

    private SpindexPos[] Disarray = {SpindexPos.SHOOT_ONE, SpindexPos.SHOOT_TWO, SpindexPos.SHOOT_THREE};
    private int NextIDX = 0;

    public SpindexerShootCommand(SpindexerSubsystem spindexerSubsytem, int direction) {

        this.spindexerSubsystem = spindexerSubsytem;
        this.direction = direction;
        addRequirements(spindexerSubsytem);
    }

    @Override
    public void initialize() {
        int curIDX = 0;
        if (spindexerSubsystem.getPosition() == SpindexPos.SHOOT_TWO) {
            curIDX = 1;
        } else if (spindexerSubsystem.getPosition() == SpindexPos.SHOOT_THREE) {
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