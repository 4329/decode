package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_D;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_S;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_V;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_I;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_P;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_PERCENT;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private MotorEx shooterMotor;
    private boolean running = false;

    public ShootSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");
        setUp();

    }

    private void setUp() {
        shooterMotor.setInverted(true);
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

            Log.i("pct/MAX_TICKS/vel/accel", String.format("%f, %f, %f, %f", SHOOTER_PERCENT, shooterMotor.ACHIEVABLE_MAX_TICKS_PER_SECOND, shooterMotor.getCorrectedVelocity(), shooterMotor.getAcceleration()));
        }
        telemetry.addData("speedy", shooterMotor.getVelocity());
    }
}
