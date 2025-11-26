package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.CLOSE_LEFT;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.CLOSE_RIGHT;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.OPEN_LEFT;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.OPEN_RIGHT;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.util.SpindexPos;

import java.net.ServerSocket;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo inTakeMotor;
    private Servo leftDoor;
    private Servo rightDoor;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        this.inTakeMotor = hardwareMap.get(CRServo.class,"intake");
        this.leftDoor = hardwareMap.get(Servo.class, "leftDoor");
        this.rightDoor = hardwareMap.get(Servo.class, "rightDoor");
        close();
    }
    public void on( ) {
        inTakeMotor.setPower(1);
    }
    public void off( ) {
        inTakeMotor.setPower(0);
    }
    public void close() {
        leftDoor.setPosition(CLOSE_LEFT);
        rightDoor.setPosition(CLOSE_RIGHT);
    }
    public void open() {
        leftDoor.setPosition(OPEN_LEFT);
        rightDoor.setPosition(OPEN_RIGHT);
    }
}
