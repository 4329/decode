package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.InitializeNavxCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskCommand;
import org.firstinspires.ftc.teamcode.command.ObeliskSpinCommand;
import org.firstinspires.ftc.teamcode.subsystem.BlinkinSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotState;

import java.util.Map;

public abstract class CloseAuto extends PedroAuto {
  private ImuSubsystem imuSubsystem;
  private LimeLightSubsystem limeLightSubsystem;
  private SpindexerSubsystem spindexerSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
  private BlinkinSubsystem BlinkyguySubsystem;
  private RobotState robotState = new RobotState();

  @Override
  public void initialize() {
    super.initialize();

    Map<String, PathChain> paths = pathFactory.getClosePaths();

    imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
    spindexerSubsystem = new SpindexerSubsystem(hardwareMap, telemetry);
    shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
    pushyMcPushermanSubsystem = new PushyMcPushermanSubsystem(hardwareMap, telemetry);
    BlinkyguySubsystem = new BlinkinSubsystem(hardwareMap, telemetry, getAlliance());
    limeLightSubsystem = new LimeLightSubsystem(hardwareMap, telemetry, getAlliance(), BlinkyguySubsystem::tagInSight);

    AutoCommandFactory factory = new AutoCommandFactory(null, imuSubsystem, telemetry, limeLightSubsystem, pushyMcPushermanSubsystem, shooterSubsystem, spindexerSubsystem, getAlliance(), voltageSubsystem);

    SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
        //new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
        new ParallelCommandGroup(
            new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_OBELISK_READ)),
            new SequentialCommandGroup(
                new WaitCommand(500),
                new ObeliskCommand(limeLightSubsystem, telemetry, robotState)
            )
        ),
        new ParallelCommandGroup(
            new ObeliskSpinCommand(spindexerSubsystem, robotState),
            new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_SHOOTY), true)
        ),
        new WaitCommand(500),
        factory.tripleShotEspresso().withTimeout(8000),
        new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_OFF_LINE), true)

    );

    register(imuSubsystem, limeLightSubsystem, BlinkyguySubsystem);
    schedule(autoCommandGroup);
  }

  public abstract Alliance getAlliance();
}
