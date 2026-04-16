package org.firstinspires.ftc.teamcode.opmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.InitializeNavxCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskSpinCommand;
import org.firstinspires.ftc.teamcode.command.TurnToHeadingCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
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
import org.firstinspires.ftc.teamcode.util.MathUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

public abstract class CloseAuto extends CommandOpMode {
    private PathChain obeliskRead;
    private PathChain shooty;
    private PathChain offTheLine;

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

    private Follower follower;

    private void configurePaths() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(20.8, 121.7, Math.toRadians(-130)));

        /*
        obeliskRead = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(20.800, 121.700),
                                new Pose(65.230, 121.785),
                                new Pose(62.921, 107.913)
                        )
                ).setTangentHeadingInterpolation()
                .build();

        shooty = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(62.921, 107.913),
                                new Pose(61.596, 83.617)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-101), Math.toRadians(-130))
                .build();

        offTheLine = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(61.596, 83.617),
                                new Pose(40.426, 103.830),
                                new Pose(22.200, 100.100)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-101), Math.toRadians(-90))
                .build();
         */


        obeliskRead = follower.pathBuilder().addPath(
                        new BezierCurve(
                                toPose(20.800, 121.700),
                                toPose(65.230, 121.785),
                                toPose(62.921, 107.913)
                        )
                ).setTangentHeadingInterpolation()
                .build();

        shooty = follower.pathBuilder().addPath(
                        new BezierLine(
                                toPose(62.921, 107.913),
                                toPose(61.596, 83.617)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-101), Math.toRadians(-130))
                .build();

        offTheLine = follower.pathBuilder().addPath(
                        new BezierCurve(
                                toPose(61.596, 83.617),
                                toPose(40.426, 103.830),
                                toPose(22.200, 100.100)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(-101), Math.toRadians(-90))
                .build();
    }

    @Override
    public void initialize() {
        configurePaths();

        LoggingUtil.enableCommandLogging();
        telemetry.speak("running" + getClass().getSimpleName());
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        spindexerSubsystem = new SpindexerSubsystem(hardwareMap, telemetry);
        shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
        pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap, telemetry);
        BlinkyguySubsystem = new BlinkinSubsystem(hardwareMap,telemetry,getAlliance());
        limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), BlinkyguySubsystem::tagInSight);
        voltageSubsystem = new VoltageSubsystem(hardwareMap);

        AutoCommandFactory factory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance(), voltageSubsystem);

        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
                new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
                new ParallelCommandGroup(
                    new FollowPathCommand(follower, obeliskRead),
                    new SequentialCommandGroup(
                            new WaitCommand(100),
                            new ObeliskCommand(limeLightSubsystem, telemetry, robotState)
                    )
                ),
                new ParallelCommandGroup(
                        new ObeliskSpinCommand(spindexerSubsystem, robotState),
                        new FollowPathCommand(follower, shooty)
                ),
                new WaitCommand(500),
                factory.tripleShotEspresso().withTimeout(8000),
                new FollowPathCommand(follower, offTheLine)
        );

        register(telemetryUpdateSubsystem, imuSubsystem, limeLightSubsystem, BlinkyguySubsystem, voltageSubsystem);
        schedule(autoCommandGroup);

    }

    public Pose toPose(double x, double y) {
        return Alliance.BLUE.equals(getAlliance())
                ? new Pose(x, y)
                : MathUtil.toRedPose(x, y);
    }

    public abstract Alliance getAlliance();
}
