package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name = "Red drive only close auto", group = "red")
public class RedThreePointCloseAuto extends ThreePointCloseAuto {
  @Override
  public Alliance getAlliance() {
    return Alliance.RED;
  }
}
