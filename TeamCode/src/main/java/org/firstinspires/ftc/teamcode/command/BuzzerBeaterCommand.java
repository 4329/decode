package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;

import java.util.concurrent.TimeUnit;

public class BuzzerBeaterCommand extends CommandBase {

    private final int timeoutMs;
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private final Deadline deadline;

    public BuzzerBeaterCommand(int timeoutMs, PushyMcPushermanSubsystem pushyMcPushermanSubsystem) {
        this.timeoutMs = timeoutMs;
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
        this.deadline = new Deadline(timeoutMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public void initialize() {
        deadline.reset();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            pushyMcPushermanSubsystem.up();
        }
    }

    @Override
    public boolean isFinished() {
        return deadline.hasExpired();
    }
}
