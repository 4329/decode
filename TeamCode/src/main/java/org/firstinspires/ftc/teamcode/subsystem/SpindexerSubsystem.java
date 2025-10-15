package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.DASHBOARD_SPIN;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.SpindexPos;

public class SpindexerSubsystem extends SubsystemBase {
    private Servo storage;
    public SpindexerSubsystem(HardwareMap hardwareMap) {
        this.storage = hardwareMap.get(Servo.class, "indexer");
    }

        public void spinTo(SpindexPos position) {storage.setPosition(position.getValue()); }

        public void spin() {storage.setPosition(DASHBOARD_SPIN); }
}
