package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SpindexerSubsystem extends SubsystemBase {
    private Servo storage;
    public SpindexerSubsystem(HardwareMap hardwareMap) {
        this.storage = hardwareMap.get(Servo.class,"indexer");
    }
}
