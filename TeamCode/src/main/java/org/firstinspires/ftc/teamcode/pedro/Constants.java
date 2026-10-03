package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.OTOSConfig;
import com.pedropathing.revhub.localizers.OTOSLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
  //public static FollowerConstants followerConstants = new FollowerConstants().mass(11.3965);

  public static MecanumConfig driveConstants = new MecanumConfig(m -> {
    m.powerThreshold.set(1.0);
    m.frontRightName.set("rightFrontDrive");
    m.backRightName.set("rightBackDrive");
    m.frontLeftName.set("leftFrontDrive");
    m.backLeftName.set("leftBackDrive");
    m.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
    m.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
    m.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
    m.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
  });

  public static OTOSConfig otosConstants = new OTOSConfig(c -> {
    c.name.set("otos");
    c.linearUnit.set(DistanceUnit.INCH);
    // c.angleUnit.set(AngleUnit.RADIANS);
    c.linearScalar.set(1.04708);
    c.angularScalar.set(1.00037);
    c.offset.set(new Pose(0.0, 0.0));
  });

  //public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

  public static ForesightConfig foresightConfig = new ForesightConfig(f -> {
    f.maxAchievableForwardVelocity.set(55.74026);
    f.maxAchievableStrafeVelocity.set(35.3115);
  });

  public static Follower createFollower(HardwareMap hardwareMap) {
    return new Follower(
        new OTOSLocalizer(hardwareMap, otosConstants),
        new Mecanum(hardwareMap, driveConstants),
        new Foresight(foresightConfig)
    );
  }
}