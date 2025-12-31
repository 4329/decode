package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;

public class ShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final boolean stopAfterShot;

    public ShootCommand(ShooterSubsystem shooterSubsystem, boolean stopAfterShot) {
        this.shooterSubsystem = shooterSubsystem;
        this.stopAfterShot = stopAfterShot;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.shoot();
    }

    @Override
    public void end(boolean interrupted) {
        if (stopAfterShot) {
            shooterSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}