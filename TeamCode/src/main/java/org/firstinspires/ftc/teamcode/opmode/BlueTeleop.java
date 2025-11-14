package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Alliance;
@TeleOp(name="BlueTeleop")
public class BlueTeleop extends Teleop {
    @Override
    public Alliance getAlliance() {
        return Alliance.BLUE; }
}
