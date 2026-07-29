package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name = "Red Close Auto", group = "red")
public class RedCloseAuto extends CloseAuto {

  @Override
  public Alliance getAlliance() {
    return Alliance.RED;
  }
}
