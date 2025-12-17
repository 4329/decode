package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.RAMP_DOWN;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.RAMP_UP;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RampSubsystem extends SubsystemBase{
    private Servo ramp;
    public RampSubsystem(HardwareMap hardwareMap) {
        this.ramp = hardwareMap.get(Servo.class, "ramp");
        up();
    }
    public void up(){
        ramp.setPosition(RAMP_UP);
    }
    public void down(){
        ramp.setPosition(RAMP_DOWN);
    }
}
