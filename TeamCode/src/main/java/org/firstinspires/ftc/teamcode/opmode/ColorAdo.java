package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.InitializeNavxCommand;
import org.firstinspires.ftc.teamcode.command.LineStuffUpCommand;
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
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;

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
//        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), BlinkyguySubsystem::tagInSight);
        AutoCommandFactory factory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance());
        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
            new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
                new SpindexerCommand(spindexerSubsystem, 1),
                factory.rMove(10,0),
                factory.tripleShotEspresso().withTimeout(12000),
                factory.rMove(10,5)
        );

        register(telemetryUpdateSubsystem, imuSubsystem);
        schedule(autoCommandGroup);
    }
    public abstract Alliance getAlliance();

}
