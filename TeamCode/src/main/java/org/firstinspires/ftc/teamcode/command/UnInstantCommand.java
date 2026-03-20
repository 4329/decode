package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;

public class UnInstantCommand extends CommandBase {
    private final Runnable m_toRun;

    public UnInstantCommand(Runnable toRun, Subsystem... requirements) {
        m_toRun = toRun;

        addRequirements(requirements);
    }

    @Override
    public void execute() {
        m_toRun.run();
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

}
