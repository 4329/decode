package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.REGULAR_DRIVE_DIVISOR;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SLOW_DRIVE_DIVISOR;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;

import java.util.function.Supplier;

public class MecanumDriveCommand extends CommandBase {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private Supplier<Double> forwardDrive;
    private Supplier<Double> strafeDrive;
    private Supplier<Double> turnDrive;
    private Supplier<Boolean> speedBoost;
    private Supplier<Boolean> slowMode;

    public MecanumDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Supplier<Double> strafeDrive, Supplier<Double> forwardDrive, Supplier<Double> turnDrive, Supplier<Boolean> speedBoost, Supplier<Boolean> slowMode) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.strafeDrive = strafeDrive;
        this.forwardDrive = forwardDrive;
        this.turnDrive = turnDrive;
        this.speedBoost = speedBoost;
        this.slowMode = slowMode;
        addRequirements(mecanumDriveSubsystem);
    }

    @Override
    public void execute() {
        if (speedBoost.get()) {
            mecanumDriveSubsystem.drive(
                    strafeDrive.get(),
                    forwardDrive.get(),
                    turnDrive.get());
        }
        else if(slowMode.get()) {
            mecanumDriveSubsystem.drive(
                    strafeDrive.get() / SLOW_DRIVE_DIVISOR,
                    forwardDrive.get() / SLOW_DRIVE_DIVISOR,
                    turnDrive.get() / SLOW_DRIVE_DIVISOR
            );
        }
        else {
            mecanumDriveSubsystem.drive(
                    strafeDrive.get() / REGULAR_DRIVE_DIVISOR,
                    forwardDrive.get() / REGULAR_DRIVE_DIVISOR,
                    turnDrive.get() / REGULAR_DRIVE_DIVISOR
            );
        }
    }
}
