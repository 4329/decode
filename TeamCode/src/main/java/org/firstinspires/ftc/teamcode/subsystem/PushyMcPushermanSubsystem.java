package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_DOWN;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_UP;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class PushyMcPushermanSubsystem extends SubsystemBase {
    private final TouchSensor buttonUp;
    private final TouchSensor buttonDown;
    private Servo push;
    public PushyMcPushermanSubsystem(HardwareMap hardwareMap) {
        this.push = hardwareMap.get(Servo.class, "push");
        this.buttonUp = hardwareMap.get(TouchSensor.class, "up");
        this.buttonDown = hardwareMap.get(TouchSensor.class, "down");
        down();
    }
    public void up(){
        push.setPosition(PUSHY_UP);
    }
    public void down(){
        push.setPosition(PUSHY_DOWN);
    }
    public boolean itIsDown(){
        return buttonDown.isPressed();
    }
    public boolean itIsUp(){
        return buttonUp.isPressed();
    }
}
