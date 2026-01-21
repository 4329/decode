package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.REGULAR_DRIVE_DIVISOR;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SLOW_DRIVE_DIVISOR;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.util.RobotState;

import java.util.function.Supplier;

public class MecanumDriveCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Supplier<Double> forwardDrive;
    private Supplier<Double> strafeDrive;
    private Supplier<Double> turnDrive;
    private Supplier<Boolean> speedBoost;
    private Supplier<Boolean> slowMode;
    private final ImuSubsystem imuSubsystem;
    private final RobotState robotState;
    private final Telemetry telemetry;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Supplier<Double> strafeDrive, Supplier<Double> forwardDrive, Supplier<Double> turnDrive, Supplier<Boolean> speedBoost, Supplier<Boolean> slowMode, ImuSubsystem imuSubsystem, RobotState robotState, Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.strafeDrive = strafeDrive;
        this.forwardDrive = forwardDrive;
        this.turnDrive = turnDrive;
        this.speedBoost = speedBoost;
        this.slowMode = slowMode;
        this.imuSubsystem = imuSubsystem;
        this.robotState = robotState;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        double strafe, forward, turn;

        if (speedBoost.get()) {
                    strafe = strafeDrive.get();
                    forward = forwardDrive.get();
                    turn = turnDrive.get();
        }
        else if(slowMode.get()) {
                    strafe = strafeDrive.get() / SLOW_DRIVE_DIVISOR;
                    forward = forwardDrive.get() / SLOW_DRIVE_DIVISOR;
                    turn = turnDrive.get() / SLOW_DRIVE_DIVISOR;

        }
        else {
                    strafe =strafeDrive.get() / REGULAR_DRIVE_DIVISOR;
                    forward = forwardDrive.get() / REGULAR_DRIVE_DIVISOR;
                    turn = turnDrive.get() / REGULAR_DRIVE_DIVISOR;
        }
        if (robotState.isRobotOriented()) {
            mecanumDriveSubsystem.drive (strafe, forward, turn);
        }
        else {
            mecanumDriveSubsystem.driveFieldOriented(strafe, forward, turn, imuSubsystem.getHeading());
        }
        telemetry.addData("Drive Mode is ", robotState.isRobotOriented()? "robot oriented": "field oriented");
    }
}
