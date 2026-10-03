package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.MathUtil;

import java.util.Map;

public class PathFactory {
  public enum PathName {
    CLOSE_OBELISK_READ,
    CLOSE_SHOOTY,
    CLOSE_OFF_LINE
  }

  public static final String CLOSE_OBELISK_READ = "close_obby_read";
  public static final String CLOSE_SHOOTY = "close_shooty";
  public static final String CLOSE_OFF_LINE = "close_off_the_line";
  private final Follower follower;
  private final Alliance alliance;

  private Pose closeStartingPosition, closeObeliskReadPosition, closeShootPosition, closeOffTheLinePosition;

  public PathFactory(Follower follower, Alliance alliance) {
    this.follower = follower;
    this.alliance = alliance;
    initPaths();
  }

  private void initPaths() {
    closeStartingPosition = alliancePose(20.8, 121.7, 227);
    closeObeliskReadPosition = alliancePose(62.921, 107.914, 180);
    closeShootPosition = alliancePose(61.596, 83.617, 227);
    closeOffTheLinePosition = alliancePose(22.200, 100.100, 270);
  }

  public Map<PathName, Path> getClosePaths() {
    follower.setPose(closeStartingPosition);
    Path obeliskRead = Paths.line(closeStartingPosition, closeObeliskReadPosition)
        .linear(closeStartingPosition, closeObeliskReadPosition);

    Path shooty = Paths.line(closeObeliskReadPosition, closeShootPosition)
        .linear(closeObeliskReadPosition, closeShootPosition);

    Path offTheLine = Paths.line(closeShootPosition, closeOffTheLinePosition)
        .linear(closeShootPosition, closeOffTheLinePosition);

    return Map.of(
        PathName.CLOSE_OBELISK_READ, obeliskRead,
        PathName.CLOSE_SHOOTY, shooty,
        PathName.CLOSE_OFF_LINE, offTheLine
    );
  }

  private double allianceAngle(double h) {
    return Alliance.BLUE.equals(alliance)
        ? Math.toRadians(h)
        : MathUtil.toRedRadians(h);
  }

  private Pose alliancePose(double x, double y, double h) {
    if (Alliance.BLUE.equals(alliance)) {
      return new Pose(x, y, Math.toRadians(h));
    } else {
      Pose xyPose = MathUtil.toRedPose(x, y);
      return new Pose(xyPose.x(), xyPose.y(), MathUtil.toRedRadians(h));
    }
  }
}
