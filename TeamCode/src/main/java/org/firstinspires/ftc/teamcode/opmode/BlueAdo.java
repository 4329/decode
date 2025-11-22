package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name = "Blue autodaddy")
public class BlueAdo extends ColorAdo {
    @Override
    public Alliance getAlliance() {
        return Alliance.BLUE;
    }
}
