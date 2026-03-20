package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;

public class PushyDownCommand extends CommandBase {
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;

    public PushyDownCommand(PushyMcPushermanSubsystem pushyMcPushermanSubsystem) {
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
        addRequirements(pushyMcPushermanSubsystem);
    }

    @Override
    public void initialize() {
        pushyMcPushermanSubsystem.down();
    }

    @Override
    public boolean isFinished() {
        return pushyMcPushermanSubsystem.itIsDown();
    }
}
