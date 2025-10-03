package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.REGULAR_DRIVE_DIVISOR;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;

import java.util.function.BooleanSupplier;

public class MecanumDpadCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private BooleanSupplier speedBooost;
    private final int forward;
    private final int strafe;
    private Telemetry telemetry;

    public MecanumDpadCommand(MecanumDriveSubsystem mecanumDriveSubsystem, BooleanSupplier speedBooost,
                              int forward, int strafe, Telemetry telemetry) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.speedBooost = speedBooost;
        this.forward = forward;
        this.strafe = strafe;
        this.telemetry = telemetry;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        telemetry.addData("speedBooost", speedBooost.getAsBoolean());
        if (speedBooost.getAsBoolean()) {
            if (Math.abs(forward) > 0) {
                mecanumDriveSubsystem.drive( forward, 0, 0);
            } else {
                mecanumDriveSubsystem.drive( 0, 0, strafe);
            }
        } else {
            if (Math.abs(forward) > 0) {
                mecanumDriveSubsystem.drive( forward / REGULAR_DRIVE_DIVISOR, 0, 0);
            } else {
                mecanumDriveSubsystem.drive( 0, 0, strafe / REGULAR_DRIVE_DIVISOR);
            }
        }
    }
}