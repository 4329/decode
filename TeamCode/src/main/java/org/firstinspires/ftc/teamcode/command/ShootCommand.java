package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ShootSubsystem;

public class ShootCommand extends CommandBase {
    private final ShootSubsystem shootSubsystem;

    public ShootCommand(ShootSubsystem shootSubsystem) {
        this.shootSubsystem = shootSubsystem;
    addRequirements(shootSubsystem);
    }

    @Override
    public void execute() {
        shootSubsystem.shoot();
    }

    @Override
    public void end(boolean interrupted) {
        shootSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}