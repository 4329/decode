package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;

public class InitializeNavxCommand extends CommandBase {

    private ImuSubsystem imuSubsystem;
    private Telemetry telemetry;

    public InitializeNavxCommand(ImuSubsystem imuSubsystem, Telemetry telemetry) {
        this.imuSubsystem = imuSubsystem;
        this.telemetry = telemetry;
        addRequirements(imuSubsystem);
    }

    @Override
    public void initialize() {
        telemetry.addData("navx", "Navx Gyro calibrating.");
    }

    @Override
    public void execute() {
         telemetry.addData("navx", "calibrating");
    }

    @Override
    public void end(boolean interrupted) {
        telemetry.log().clear();
        if (interrupted) {
            telemetry.addData("navx","Gyro timed out while trying to calibrate");
        }
        else {
            telemetry.addData("navx", "Gyro calibrated");
        }
    }

    @Override
    public boolean isFinished() {
        return !imuSubsystem.isCalibrating();
    }
}
