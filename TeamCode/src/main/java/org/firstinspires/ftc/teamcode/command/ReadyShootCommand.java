package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FAR_GOAL;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;

public class ReadyShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final Telemetry telemetry;
    private double velocity;

    public ReadyShootCommand(ShooterSubsystem shooterSubsystem, Telemetry telemetry) {
        this.shooterSubsystem = shooterSubsystem;
        this.telemetry = telemetry;
    }

    @Override
    public void execute() {
        velocity = shooterSubsystem.getVelocity();
        telemetry.addData("currentShootSpeed", velocity);
    }

    @Override
    public boolean isFinished() {
        if (velocity >= SHOOTER_FAR_GOAL){
            Log.i("readyShoot", String.format("GOAL REACHED velocity/Goal:  %f, %f", velocity, SHOOTER_FAR_GOAL));
            return true;
        }
        else{
            Log.i("readyShoot", String.format("velocity/Goal:  %f, %f", velocity, SHOOTER_FAR_GOAL));
            return false;
        }
    }
}
