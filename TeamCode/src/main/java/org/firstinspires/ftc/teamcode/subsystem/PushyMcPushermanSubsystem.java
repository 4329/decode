package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_DOWN;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.PUSHY_UP;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PushyMcPushermanSubsystem extends SubsystemBase {
    private final TouchSensor buttonUp;
    private final TouchSensor buttonDown;
    private final Telemetry telemetry;
    private Servo push;
    public PushyMcPushermanSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.push = hardwareMap.get(Servo.class, "push");
        this.buttonUp = hardwareMap.get(TouchSensor.class, "up");
        this.buttonDown = hardwareMap.get(TouchSensor.class, "down");
        this.telemetry = telemetry;
        down();
    }
    public void up(){
        push.setPosition(PUSHY_UP);
    }
    public void down(){
        push.setPosition(PUSHY_DOWN);
    }

    @Override
    public void periodic() {
        telemetry.addData("Up", buttonUp.isPressed());
        telemetry.addData("Down", buttonDown.isPressed());
    }

    public boolean itIsDown(){
        return buttonDown.isPressed();
    }
    public boolean itIsUp(){
        return buttonUp.isPressed();
    }
}
