package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_DOWN;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_UP;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PushyMcPushermanSubsystem extends SubsystemBase {
    private Servo push;
    public PushyMcPushermanSubsystem(HardwareMap hardwareMap) {
        this.push = hardwareMap.get(Servo.class, "push");
    }
    public void up(){
        push.setPosition(PUSHY_UP);
    }
    public void down(){
        push.setPosition(PUSHY_DOWN);
    }
}
