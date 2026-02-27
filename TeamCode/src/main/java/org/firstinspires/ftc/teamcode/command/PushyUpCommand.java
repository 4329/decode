package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;

public class PushyUpCommand extends CommandBase {
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;

    public PushyUpCommand(PushyMcPushermanSubsystem pushyMcPushermanSubsystem) {
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
        addRequirements(pushyMcPushermanSubsystem);
    }

    @Override
    public void initialize() {
        pushyMcPushermanSubsystem.up();
    }

    @Override
    public boolean isFinished() {
        return pushyMcPushermanSubsystem.itIsUp();
    }
}
