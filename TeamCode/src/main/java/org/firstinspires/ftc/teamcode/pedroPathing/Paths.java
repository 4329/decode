package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {
    private PathChain obeliskread;
    private PathChain shooty;
    private PathChain offline;

    public Paths(Follower follower) {
        follower.setStartingPose(new Pose(20.8, 121.7, Math.toRadians(-130)));

        obeliskread = follower.pathBuilder().addPath(
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

        offline = follower.pathBuilder().addPath(
                    new BezierCurve(
                        new Pose(61.596, 83.617),
                        new Pose(40.426, 103.830),
                        new Pose(22.200, 100.100)
                    )
                ).setLinearHeadingInterpolation(Math.toRadians(-101), Math.toRadians(-90))
                .build();
    }

}
