package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;
@Autonomous(name = "Red autodaddy")
public class RedAdo extends ColorAdo {
    @Override
    public Alliance getAlliance() {
        return Alliance.RED;
    }
}
