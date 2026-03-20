package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;

public class IsFunishootedCommand extends CommandBase {
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;

    public IsFunishootedCommand(PushyMcPushermanSubsystem pushyMcPushermanSubsystem) {
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
    }

    @Override
    public void end(boolean interrupted) {
        pushyMcPushermanSubsystem.down();
    }
}
