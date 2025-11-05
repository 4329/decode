package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.DASHBOARD_SPIN;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.SpindexPos;

public class SpindexerSubsystem extends SubsystemBase {
    private Servo storage;
    private Telemetry telemetry;
    private SpindexPos position;

    public SpindexerSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.storage = hardwareMap.get(Servo.class, "indexer");
        this.telemetry=telemetry;
    }

    public SpindexPos getPosition() {
        return position;
    }

    public void spinTo(@NonNull SpindexPos position) {
            this.position = position;
            storage.setPosition(position.getValue()); }

        public void spin() {
            storage.setPosition(DASHBOARD_SPIN);
            telemetry.addData("spindex",DASHBOARD_SPIN);
    }
}
