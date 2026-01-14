package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.InitializeNavxCommand;
import org.firstinspires.ftc.teamcode.command.LineStuffUpCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskSpinCommand;
import org.firstinspires.ftc.teamcode.command.SpindexerCommand;
import org.firstinspires.ftc.teamcode.command.TurnToHeadingCommand;
import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

public abstract class ColorAdo extends CommandOpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private TurnToHeadingCommand turnToHeadingCommand;
    private LimeLightSubsystem limeLightSubsystem;
    private SpindexerSubsystem spindexerSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private BlinkinSubsystem BlinkyguySubsystem;
    private VoltageSubsystem voltageSubsystem;
    private RobotState robotState = new RobotState();

    @Override
    public void initialize() {
        telemetry.speak("running" + getClass().getSimpleName());
        LoggingUtil.enableCommandLogging();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        spindexerSubsystem = new SpindexerSubsystem(hardwareMap, telemetry);
        shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
        pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap);
        BlinkyguySubsystem = new BlinkinSubsystem(hardwareMap,telemetry,getAlliance());
        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), BlinkyguySubsystem::tagInSight);
        voltageSubsystem = new VoltageSubsystem(hardwareMap);
        AutoCommandFactory factory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance());
        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
            new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
                new ObeliskCommand(limeLightSubsystem, telemetry, robotState),
                 new ParallelCommandGroup(
                         factory.rMove(10,0),
                         new ObeliskSpinCommand(spindexerSubsystem, robotState)
                 ),
                factory.tripleShotEspresso().withTimeout(12000),
                factory.rMove(15,5*getAlliance().value)
        );

        register(telemetryUpdateSubsystem, imuSubsystem, limeLightSubsystem, BlinkyguySubsystem, voltageSubsystem);
        schedule(autoCommandGroup);
    }
    public abstract Alliance getAlliance();

}
