package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.EXPECTED_BATTERY_VOLTAGE;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.ArrayList;
import java.util.List;

public class VoltageSubsystem extends SubsystemBase {
    private List<VoltageSensor> sensors = null;

    public VoltageSubsystem(HardwareMap hardwareMap) {
        sensors = hardwareMap.getAll(VoltageSensor.class);
    }

    @Override
    public void periodic() {
        for (VoltageSensor sensor : sensors) {
            Log.i("voltage", String.format("(sensor %s): %fV", sensor.getDeviceName(), sensor.getVoltage()));
        }
    }

    public double getVoltage() {
        // TODO - there's probably just one voltage sensor on the robot... just returning the first one now.
        if (sensors != null && !sensors.isEmpty()) {
            return sensors.get(0).getVoltage();
        }
        return 0;
    }

    public double getVoltageCompensationFactor() {
       double voltage = getVoltage();
       if (voltage == 0) {
           return 1;
       } else if (voltage >= EXPECTED_BATTERY_VOLTAGE) {
           return 1;
       } else {
           return EXPECTED_BATTERY_VOLTAGE / voltage;
       }
    }
}
