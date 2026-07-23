package org.firstinspires.ftc.teamcode.opmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

@Autonomous(name="pedro-auto", group="pedro")
public class PedroAuto extends CommandOpMode {
    private PathChain obeliskRead;
    private PathChain shooty;
    private PathChain offTheLine;

    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private VoltageSubsystem voltageSubsystem;
    private RobotState robotState = new RobotState();

    private Follower follower;

    private void configurePaths() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(20.8, 121.7, Math.toRadians(-130)));

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
    }

    @Override
    public void initialize() {
        configurePaths();

        LoggingUtil.enableCommandLogging();
        telemetry.speak("running " + getClass().getSimpleName());
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        voltageSubsystem = new VoltageSubsystem(hardwareMap);


        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
                new FollowPathCommand(follower, obeliskRead, true, .5),
                new WaitCommand(2000),
                new FollowPathCommand(follower, shooty),
                new WaitCommand(2000),
                new FollowPathCommand(follower, offTheLine)
        );

        register(telemetryUpdateSubsystem, voltageSubsystem);

       // schedule(autoCommandGroup);
        schedule(new RunCommand(() -> follower.update()));
        schedule(autoCommandGroup);
    }
}
