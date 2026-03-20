package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_D;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FAR_GOAL;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_S;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_FF_V;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_I;
import static org.firstinspires.ftc.teamcode.util.RobotConfig.SHOOTER_P;

import android.util.Log;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.MathUtil;
import org.firstinspires.ftc.teamcode.util.SpindexerMode;

import java.util.List;
import java.util.function.DoubleSupplier;

public class ShooterSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private MotorEx shooterMotor;
    private MotorEx shootorMotor;
    private MotorGroup groupOfGoop;
    private boolean running = false;
    private SpindexerMode currentMode;
    private double setpoint = 5;
    private PIDController shooterPID;
    private SimpleMotorFeedforward shooterFeedForward;
    private double voltageCompensationFactor;

    public ShooterSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        shooterMotor = new MotorEx(hardwareMap, "shooterMotor");
        shootorMotor = new MotorEx(hardwareMap, "shootorMotor");
        groupOfGoop = new MotorGroup(shooterMotor, shootorMotor);
        shooterPID = new PIDController(SHOOTER_P, SHOOTER_I, SHOOTER_D);
        shooterFeedForward = new SimpleMotorFeedforward(SHOOTER_FF_S, SHOOTER_FF_V);
        setUp();
    }

    private void setUp() {
        groupOfGoop.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        groupOfGoop.setRunMode(Motor.RunMode.RawPower);
    }

    public void stop() {
        running = false;
        groupOfGoop.stopMotor();
    }

   // public void shoot(DoubleSupplier doubleSupplier) {
       // shoot(doubleSupplier.getAsDouble(), voltageCompensationFactor);
    //}

    public void shoot(double setpoint, double voltageCompensationFactor) {
        this.setpoint = setpoint;
        this.voltageCompensationFactor = voltageCompensationFactor;
        running = true;
        shooterPID.setPID(SHOOTER_P, SHOOTER_I, SHOOTER_D);
        shooterPID.setSetPoint(setpoint);
        shooterFeedForward = new SimpleMotorFeedforward(SHOOTER_FF_S, SHOOTER_FF_V);
    }

    @Override
    public void periodic() {
        if (running) {
            double currentVelocity = shooterMotor.getCorrectedVelocity();
            double pidOutput = shooterPID.calculate(currentVelocity);
            double ffOutput = shooterFeedForward.calculate(currentVelocity, shooterMotor.getAcceleration());
            double clamped = MathUtil.clamp((pidOutput + ffOutput)*voltageCompensationFactor, -1.0, 1.0);

            Log.i("vel/ffOutput/pidOutput/clamped", String.format("%f, %f, %f, %f", currentVelocity, ffOutput, pidOutput, clamped));
            telemetry.addData("ffOutput", ffOutput);
            telemetry.addData("pidOutput", pidOutput);
            telemetry.addData("shooter output", clamped);

            groupOfGoop.set(clamped);
        }
        List<Double> velocities = groupOfGoop.getVelocities();
        telemetry.addData("speedy", velocities.get(0));
        telemetry.addData("notspeedy", velocities.get(1));
    }
    public double getVelocity(){
        return groupOfGoop.getVelocity();
    }

    public void changeMode(SpindexerMode newMode) {
        this.currentMode = newMode;
        if (SpindexerMode.SHOOT.equals(newMode)) {
            shoot(SHOOTER_FAR_GOAL, 1);
        }
        else {
            stop();
        }
    }
}