package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.MathUtil;

import java.util.Map;

public class PathFactory {
  public static final String CLOSE_OBELISK_READ = "close_obby_read";
  public static final String CLOSE_SHOOTY = "close_shooty";
  public static final String CLOSE_OFF_LINE = "close_off_the_line";
  private final Follower follower;
  private final Alliance alliance;

  public PathFactory(Follower follower, Alliance alliance) {
    this.follower = follower;
    this.alliance = alliance;
  }

  public Map<String, PathChain> getClosePaths() {
    follower.setStartingPose(alliancePose(20.8, 121.7, 227));

    PathChain obeliskRead = follower.pathBuilder().addPath(
            new BezierCurve(
                alliancePose(20.800, 121.700),
                alliancePose(65.230, 121.785),
                alliancePose(62.921, 107.913)
            )
        ).setLinearHeadingInterpolation(allianceAngle(227), allianceAngle(180))
        .build();

    PathChain shooty = follower.pathBuilder().addPath(
            new BezierLine(
                alliancePose(62.921, 107.913),
                alliancePose(61.596, 83.617)
            )
        ).setLinearHeadingInterpolation(allianceAngle(180), allianceAngle(227))
        .build();

    PathChain offTheLine = follower.pathBuilder().addPath(
            new BezierCurve(
                alliancePose(61.596, 83.617),
                alliancePose(40.426, 103.830),
                alliancePose(22.200, 100.100)
            )
        ).setLinearHeadingInterpolation(allianceAngle(227), allianceAngle(270))
        .build();

    return Map.of(
        CLOSE_OBELISK_READ, obeliskRead,
        CLOSE_SHOOTY, shooty,
        CLOSE_OFF_LINE, offTheLine
    );
  }

  private Pose alliancePose(double x, double y) {
    return Alliance.BLUE.equals(alliance)
        ? new Pose(x, y)
        : MathUtil.toRedPose(x, y);
  }

  private double allianceAngle(double h) {
    return Alliance.BLUE.equals(alliance)
        ? h
        : MathUtil.toRedDegrees(h);
  }

  private Pose alliancePose(double x, double y, double h) {
    if (Alliance.BLUE.equals(alliance)) {
      return new Pose(x, y, h);
    } else {
      Pose xyPose = MathUtil.toRedPose(x, y);
      return new Pose(xyPose.getX(), xyPose.getY(), MathUtil.toRedDegrees(h));
    }
  }
}
