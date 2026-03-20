package org.firstinspires.ftc.teamcode.subsystem;

import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryUpdateSubsystem extends SubsystemBase {
    private final Telemetry telemetry;

    public TelemetryUpdateSubsystem(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
       telemetry.update();
    }
}
