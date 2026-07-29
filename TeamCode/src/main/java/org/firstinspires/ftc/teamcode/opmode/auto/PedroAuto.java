package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.LoggingUtil;
import org.firstinspires.ftc.teamcode.util.RobotState;

public abstract class PedroAuto extends CommandOpMode {
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  protected VoltageSubsystem voltageSubsystem;
  protected RobotState robotState = new RobotState();

  protected Follower follower;
  protected PathFactory pathFactory;

  @Override
  public void initialize() {
    follower = Constants.createFollower(hardwareMap);
    pathFactory = new PathFactory(follower, getAlliance());
    LoggingUtil.enableCommandLogging();

    telemetry.speak("running " + getClass().getSimpleName());
    telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
    voltageSubsystem = new VoltageSubsystem(hardwareMap);

    register(telemetryUpdateSubsystem, voltageSubsystem);
    schedule(new RunCommand(() -> follower.update()));
  }

  public abstract Alliance getAlliance();
}
