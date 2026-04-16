package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="pedro Blue Close Auto", group="pedro")
public class BlueCloseAuto extends CloseAuto {
    @Override
    public Alliance getAlliance() {
        return Alliance.BLUE;
    }
}
