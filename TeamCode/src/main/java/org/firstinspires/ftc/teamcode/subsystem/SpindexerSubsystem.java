package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.DASHBOARD_SPIN;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.SpindexPos;
import org.firstinspires.ftc.teamcode.util.SpindexerMode;

public class SpindexerSubsystem extends SubsystemBase {
    private Servo storage;
    private Telemetry telemetry;
    private SpindexPos position = SpindexPos.SHOOT_ONE;
    private SpindexerMode spindexerMode = SpindexerMode.SHOOT;

        private SpindexPos[] Disarray = {SpindexPos.SHOOT_ONE, SpindexPos.SHOOT_TWO, SpindexPos.SHOOT_THREE};
        private SpindexPos[] Datarray = {SpindexPos.INTAKE_ONE, SpindexPos.INTAKE_TWO, SpindexPos.INTAKE_THREE};

    public SpindexerSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.storage = hardwareMap.get(Servo.class, "spindexer");
        this.telemetry=telemetry;
    }

    public SpindexPos getPosition() {
        return position;
    }

    public void spinTo(@NonNull SpindexPos position) {
            this.position = position;
            storage.setPosition(position.getValue());
            telemetry.addData("spindexer", position.getValue());
    }

    public void spin() {
        storage.setPosition(DASHBOARD_SPIN);
        telemetry.addData("spindexer",DASHBOARD_SPIN);
    }
    public void spinFirst() {
        if (SpindexerMode.SHOOT == spindexerMode) {
            spinTo(SpindexPos.SHOOT_ONE);
        }
        else {
            spinTo(SpindexPos.INTAKE_ONE);
        }
    }
    public void changeMode(SpindexerMode newMode) {
        this.spindexerMode = newMode;
    }
    public void right() {
        int nextIDX = 1;
        if (position == SpindexPos.SHOOT_TWO || position == SpindexPos.INTAKE_TWO) {
            nextIDX = 2;
        } else if (position == SpindexPos.SHOOT_THREE || position == SpindexPos.INTAKE_THREE) {
            nextIDX = 0;
        }
        if (SpindexerMode.SHOOT == spindexerMode) {
            spinTo(Disarray[nextIDX]);
        } else {
            spinTo(Datarray[nextIDX]);
        }
    }
        public void left() {
            int nextIDX = 2;
            if (position == SpindexPos.SHOOT_TWO || position == SpindexPos.INTAKE_TWO) {
                nextIDX = 0;
            } else if (position == SpindexPos.SHOOT_THREE || position == SpindexPos.INTAKE_THREE) {
                nextIDX = 1;
            }
            if (SpindexerMode.SHOOT == spindexerMode) {
                spinTo(Disarray[nextIDX]);
            } else {
                spinTo(Datarray[nextIDX]);
            }
    }
}
