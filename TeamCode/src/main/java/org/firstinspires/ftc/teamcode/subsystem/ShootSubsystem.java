package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_RPM;

import android.media.midi.MidiOutputPort;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private Motor shooterMotor;
    public ShootSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        shooterMotor = new Motor(hardwareMap, "shooterMotor");
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
    }
    public void stop(){
        shooterMotor.stopMotor();
    }

    public void shoot(){
        shooterMotor.setTargetPosition(SHOOTER_RPM);

    }
}
