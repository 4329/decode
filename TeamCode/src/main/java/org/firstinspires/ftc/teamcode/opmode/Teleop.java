package org.firstinspires.ftc.teamcode.opmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.command.PushyDownCommand;
import org.firstinspires.ftc.teamcode.command.PushyUpCommand;
import org.firstinspires.ftc.teamcode.command.ShootCommand;
import org.firstinspires.ftc.teamcode.command.SpindexerCommand;
import org.firstinspires.ftc.teamcode.command.SpindexerModeeCommand;
import org.firstinspires.ftc.teamcode.command.UnInstantCommand;
import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotConfig;
import org.firstinspires.ftc.teamcode.util.RobotState;

public abstract class Teleop extends CommandOpMode {
    // FtcDashboard dashboard = FtcDashboard.getInstance();

    private GamepadEx driver, operator;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private SpindexerSubsystem spindexerSubsystem;
 //   private IntakeSubsystem intakeSubsystem;
    private LimeLightSubsystem limeLightSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private AutoCommandFactory autoCommandFactory;
    private PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private BlinkinSubsystem blinkinSubsystem;
    private VoltageSubsystem voltageSubsystem;
    private RobotState robotState = new RobotState();


    @Override
    public void initialize() {
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
        LoggingUtil.enableCommandLogging();

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        spindexerSubsystem = new SpindexerSubsystem(hardwareMap,telemetry);
       // intakeSubsystem = new IntakeSubsystem(hardwareMap);
        pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap, telemetry);
          shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
         blinkinSubsystem = new BlinkinSubsystem(hardwareMap, telemetry, getAlliance());
        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), blinkinSubsystem::tagInSight);
        voltageSubsystem = new VoltageSubsystem(hardwareMap);
        autoCommandFactory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance(), voltageSubsystem);

        MecanumDriveCommand mecanumDriveCommand = new MecanumDriveCommand(
            mecanumDriveSubsystem,
            () -> driver.getLeftX(),
            () -> driver.getLeftY(),
            () -> driver.getRightX(),
            () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
            () -> driver.getButton(GamepadKeys.Button.A),
                imuSubsystem,
                robotState,
                telemetry
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),1, 0, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, -1, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),0, 1, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem,() -> driver.getButton(GamepadKeys.Button.B),-1, 0, telemetry));
        driver.getGamepadButton(GamepadKeys.Button.START).whenPressed(new InstantCommand(()-> {
            robotState.toggleDriveMode();
            blinkinSubsystem.changeDriveMode(robotState.isRobotOriented());
        }));
        driver.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(new InstantCommand(()->imuSubsystem.reset()));

     //   operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(()-> spindexerSubsystem.spin()));
        operator.getGamepadButton(GamepadKeys.Button.Y).whenHeld(autoCommandFactory.tripleShotEspresso());

        //operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand (()-> intakeSubsystem.on()));
        //operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand (()-> intakeSubsystem.off()));
    //    operator.getGamepadButton(GamepadKeys.Button.Y).whenHeld(autoCommandFactory.scoreThingsPlease(true));
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(new PushyUpCommand(pushyMcPushermanSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(new PushyDownCommand(pushyMcPushermanSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new SpindexerCommand(spindexerSubsystem,-1));
        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new SpindexerCommand(spindexerSubsystem,1));
        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> blinkinSubsystem.celebration()));
      //  operator.getGamepadButton(GamepadKeys.Button.X).whileHeld(new InstantCommand(() -> shooterSubsystem.shoot(() -> limeLightSubsystem.getTargetVelocity())));
      //  operator.getGamepadButton(GamepadKeys.Button.START).whileHeld(new InstantCommand(() -> shooterSubsystem.stop()));
        operator.getGamepadButton(GamepadKeys.Button.Y).whenHeld(new UnInstantCommand(()->shooterSubsystem.shoot(RobotConfig.SHOOTER_FAR_GOAL, 1))).whenReleased(new InstantCommand(()->shooterSubsystem.stop()));

        operator.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(new SpindexerModeeCommand(spindexerSubsystem, shooterSubsystem, blinkinSubsystem, robotState));
        mecanumDriveSubsystem.setDefaultCommand(mecanumDriveCommand);
        register(telemetryUpdateSubsystem, imuSubsystem, limeLightSubsystem, voltageSubsystem, pushyMcPushermanSubsystem/*, blinkinSubsystem*/);
    }
    public abstract Alliance getAlliance();
}