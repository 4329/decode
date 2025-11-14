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
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private MotorEx shooterMotor;
    private MotorEx shootorMotor;
    private MotorGroup groupOfGoop;
    private boolean running = false;

    public ShootSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");
        shootorMotor = new MotorEx(hardwareMap, "shootorMotor");
        groupOfGoop = new MotorGroup(shooterMotor, shootorMotor);
        setUp();

    }

    private void setUp() {
        groupOfGoop.setInverted(true);
        groupOfGoop.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        groupOfGoop.setRunMode(Motor.RunMode.VelocityControl);
        groupOfGoop.setVeloCoefficients(SHOOTER_P, SHOOTER_I, SHOOTER_D);
        groupOfGoop.setFeedforwardCoefficients(SHOOTER_FF_S, SHOOTER_FF_V);
    }

    public void stop() {
        running = false;
        groupOfGoop.stopMotor();
    }

    public void shoot() {
        running = true;
    }

    @Override
    public void periodic() {
        if (running) {
            groupOfGoop.set(SHOOTER_PERCENT);

            Log.i("pct/MAX_TICKS/vel/accel", String.format("%f, %f, %f, %f", SHOOTER_PERCENT, groupOfGoop.ACHIEVABLE_MAX_TICKS_PER_SECOND, groupOfGoop.getCorrectedVelocity(), shooterMotor.getAcceleration()));
        }
        telemetry.addData("speedy", groupOfGoop.getCorrectedVelocity());
    }
}
