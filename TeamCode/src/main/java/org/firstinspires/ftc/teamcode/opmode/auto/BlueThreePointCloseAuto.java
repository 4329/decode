package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name = "Blue drive only Close Auto", group = "blue")
public class BlueThreePointCloseAuto extends ThreePointCloseAuto {
  @Override
  public Alliance getAlliance() {
    return Alliance.BLUE;
  }
}
