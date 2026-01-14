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
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

public abstract class OtherAuto extends CommandOpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private TurnToHeadingCommand turnToHeadingCommand;
    private LimeLightSubsystem limeLightSubsystem;
    private SpindexerSubsystem spindexerSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private BlinkinSubsystem BlinkyguySubsystem;
    private RobotState robotState = new RobotState();
    private int dellay = 0;


    @Override
    public void initialize() {
        telemetry.speak("running" + getClass().getSimpleName());
        LoggingUtil.enableCommandLogging();
        while (!isStarted() && !isStopRequested()){
            if (gamepad1.dpad_up) {
                dellay = Math.min(dellay + 500, 10000);
                sleep(200);
            }
            else if (gamepad1.dpad_down) {
                dellay = Math.max(dellay - 500, 0);
                sleep(200);
            }
            telemetry.addData("Autonomous Delay ", dellay);
            telemetry.update();
        }

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        spindexerSubsystem = new SpindexerSubsystem(hardwareMap, telemetry);
        shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
        pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap);
        BlinkyguySubsystem = new BlinkinSubsystem(hardwareMap,telemetry,getAlliance());
        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), BlinkyguySubsystem::tagInSight);
        AutoCommandFactory factory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance());
        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
                new WaitCommand(dellay),
            new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
                factory.lMove(50,0),
                new TurnToHeadingCommand(mecanumDriveSubsystem, imuSubsystem,telemetry,-60*getAlliance().value),
                new WaitCommand(500),
                new ObeliskCommand(limeLightSubsystem, telemetry, robotState),
                new ParallelCommandGroup(
                        new ObeliskSpinCommand(spindexerSubsystem, robotState),
                        new TurnToHeadingCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 0)
                        ),
                new WaitCommand(500),
                factory.tripleShotEspresso().withTimeout(8000),
                factory.allianceForward(13,0)
        );

        register(telemetryUpdateSubsystem, imuSubsystem);
        schedule(autoCommandGroup);
    }
    public abstract Alliance getAlliance();

}