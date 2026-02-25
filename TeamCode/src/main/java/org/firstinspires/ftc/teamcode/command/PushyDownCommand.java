package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;

public class PushyDownCommand extends CommandBase {
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;

    public PushyDownCommand(PushyMcPushermanSubsystem pushyMcPushermanSubsystem) {
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
    }
    @Override
    public void execute() {
        pushyMcPushermanSubsystem.down();
    }

    @Override
    public boolean isFinished() {
        return pushyMcPushermanSubsystem.itIsDown();
    }
}
