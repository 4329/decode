package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;

@TeleOp(name = "Teleop", group = "1")
public class Teleop extends CommandOpMode {
    // FtcDashboard dashboard = FtcDashboard.getInstance();

    private GamepadEx driver, operator;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private SpindexerSubsystem spindexerSubsystem;
    private IntakeSubsystem intakeSubsystem;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        spindexerSubsystem = new SpindexerSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(
            mecanumDriveSubsystem,
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            () -> driver.getLeftX(),
            () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
            () -> driver.getButton(GamepadKeys.Button.A)
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, 1, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),1, 0, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),-1, 0, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, -1, telemetry));

        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> spindexerSubsystem.spin()));
        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand (()-> intakeSubsystem.on()));
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand (()-> intakeSubsystem.off()));
        mecanumDriveSubsystem.setDefaultCommand(driveMecanumCommand);
        register(telemetryUpdateSubsystem, imuSubsystem);
    }
}