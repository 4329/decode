package org.firstinspires.ftc.teamcode.subsystem;

import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Drawing;

public class TelemetryUpdateSubsystem extends SubsystemBase {
  private Telemetry telemetry;
  private Follower follower;

  public TelemetryUpdateSubsystem(Telemetry telemetry) {
    this.telemetry = telemetry;
  }

  public TelemetryUpdateSubsystem(Telemetry telemetry, Follower follower) {
    this.telemetry = telemetry;
    this.follower = follower;
  }

  @Override
  public void periodic() {
    telemetry.update();
    if (follower != null) {
      try {
        Drawing.drawRobot(follower.getPose());
        Drawing.sendPacket();
      } catch (Exception e) {
        System.err.println("Drawing asploded");
        e.printStackTrace();
      }
    }
  }
}
