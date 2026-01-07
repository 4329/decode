package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name = "Red OtherAuto")
public class RedOtherAuto extends OtherAuto {
    @Override
    public Alliance getAlliance() {
        return Alliance.RED;
    }
}
