package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.command.ResetSpindexerCommand;
import org.firstinspires.ftc.teamcode.command.SpindexerModeeCommand;
import org.firstinspires.ftc.teamcode.command.SpindexerShootCommand;
import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

@TeleOp(name = "Graham Crackers are REALLYYYYYY GOOD...apricot")
public class Testop extends CommandOpMode {
    // FtcDashboard dashboard = FtcDashboard.getInstance();

    private GamepadEx driver, operator;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private SpindexerSubsystem spindexerSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private LimeLightSubsystem limeLightSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private AutoCommandFactory autoCommandFactory;
    private PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private BlinkinSubsystem blinkinSubsystem;
    private RobotState robotState = new RobotState();


    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        LoggingUtil.enableCommandLogging();

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
//        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
//        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
//        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
//        spindexerSubsystem = new SpindexerSubsystem(hardwareMap,telemetry);
//        intakeSubsystem = new IntakeSubsystem(hardwareMap);
//        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, Alliance.BLUE);
//          shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
 //         pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap);
          blinkinSubsystem = new BlinkinSubsystem(hardwareMap, telemetry, Alliance.RED);
//        autoCommandFactory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance());

        MecanumDriveCommand driveMecanumCommand = new MecanumDriveCommand(
            mecanumDriveSubsystem,
            () -> driver.getLeftX(),
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
            () -> driver.getButton(GamepadKeys.Button.A)
        );
//        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),1, 0, telemetry));
//        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, -1, telemetry));
//        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, 1, telemetry));
//        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),-1, 0, telemetry));
//
//                driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> spindexerSubsystem.spin()));
//
//        operator.getGamepadButton(GamepadKeys.Button.Y).whenHeld(autoCommandFactory.scoreThingsPlease());
//        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new SpindexerShootCommand(spindexerSubsystem,-1));
//        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new SpindexerShootCommand(spindexerSubsystem,1));
      //  operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(()-> pushyMcPushermanSubsystem.down());
        //operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(()-> pushyMcPushermanSubsystem.up());
          operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(new SpindexerModeeCommand(spindexerSubsystem,shooterSubsystem, blinkinSubsystem, robotState));
          operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(new ResetSpindexerCommand(spindexerSubsystem,shooterSubsystem, blinkinSubsystem, robotState));

    }
}