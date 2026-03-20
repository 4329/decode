package org.firstinspires.ftc.teamcode.subsystem;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveSubsystem extends SubsystemBase {

    private MecanumDrive mecanumDrive;
    private Motor leftFrontDrive;
    private Motor rightFrontDrive;
    private Motor leftBackDrive;
    private Motor rightBackDrive;
    private PIDController turnPID;

    public MecanumDriveSubsystem(HardwareMap hardwareMap) {
        this.leftFrontDrive = new Motor(hardwareMap, "leftFrontDrive");
        this.rightFrontDrive = new Motor(hardwareMap, "rightFrontDrive");
        this.leftBackDrive = new Motor(hardwareMap, "leftBackDrive");
        this.rightBackDrive = new Motor(hardwareMap, "rightBackDrive");

        leftFrontDrive.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontDrive.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackDrive.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackDrive.motor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mecanumDrive = new MecanumDrive(leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive);
        mecanumDrive.setRightSideInverted(false);
        turnPID = new PIDController(1, 0, 0);
        turnPID.setTolerance(15);
    }

    public void stop() {
        mecanumDrive.stop();
    }

    public Motor.Encoder getSingleEncoder() {
        return leftBackDrive.encoder;
    }

    public void drive(double strafe, double forward, double turn) {
        mecanumDrive.driveRobotCentric(strafe, forward, turn);
    }
    public void driveFieldOriented(double strafe, double forward, double turn, double gyro) {
        mecanumDrive.driveFieldCentric(strafe, forward, turn, gyro);
    }
}