package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;

public class ShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final LimeLightSubsystem limeLightSubsystem;
    private final boolean stopAfterShot;
    private final VoltageSubsystem voltageSubsystem;
    private double voltageCompensationFactor;

    public ShootCommand(ShooterSubsystem shooterSubsystem, LimeLightSubsystem limeLightSubsystem, boolean stopAfterShot, VoltageSubsystem voltageSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.limeLightSubsystem = limeLightSubsystem;
        this.stopAfterShot = stopAfterShot;
        this.voltageSubsystem = voltageSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        voltageCompensationFactor = voltageSubsystem.getVoltageCompensationFactor();
    }

    @Override
    public void execute() {
        shooterSubsystem.shoot(limeLightSubsystem.getTargetVelocity(), voltageCompensationFactor);

    }

    @Override
    public void end(boolean interrupted) {
        Log.i("SHOOT", "ShootCommand ended! interruped was: " + interrupted);
        if (stopAfterShot) {
            shooterSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}