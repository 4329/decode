package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_D;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_S;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_V;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_I;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_P;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_PERCENT;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private Motor shooterMotor;
    private boolean running = false;

    public ShootSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        shooterMotor = new Motor(hardwareMap, "shooterMotor");
        setUp();

    }

    private void setUp() {
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
        shooterMotor.setVeloCoefficients(SHOOTER_P, SHOOTER_I, SHOOTER_D);
        shooterMotor.setFeedforwardCoefficients(SHOOTER_FF_S, SHOOTER_FF_V);
    }

    public void stop() {
        running = false;
        shooterMotor.stopMotor();
    }

    public void shoot() {
        running = true;
    }

    @Override
    public void periodic() {
        if (running) {
            shooterMotor.set(SHOOTER_PERCENT);
        }
    }
}
