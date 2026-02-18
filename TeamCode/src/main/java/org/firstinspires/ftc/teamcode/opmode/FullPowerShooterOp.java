package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "RUNNNN SHOOTERRR")
public class FullPowerShooterOp extends LinearOpMode {
    private MotorEx shooter;
    private MotorEx shootor;
    private MotorGroup shoot;
    @Override
    public void runOpMode() throws InterruptedException {
        shooter = new MotorEx(hardwareMap, "shooter");
        shootor = new MotorEx(hardwareMap, "shootor");
        shoot = new MotorGroup(shooter, shootor);
        shoot.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        shoot.setRunMode(Motor.RunMode.RawPower);
        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            telemetry.addData("whjbfkzv", "Hold A to Run, Hold B to reverse");
            if(gamepad1.a) {
                shoot.set(1);
                telemetry.addData("ckjxvbxfugd", "running really fast");
            }
            else if(gamepad1.b) {
                shoot.set(-1);
                telemetry.addData("ckjxvbxfugd", "running backwards really fast");
            }
            else {
                shoot.set(0);
                telemetry.addData("ckjxvbxfugd", "not running really fast");
            }
            telemetry.addData("Velocity", shoot.getVelocity());
            telemetry.update();
        }
        shoot.set(0);
        telemetry.addData("yuftdrtxgfcnhvm", "BYEEEEE!");
        telemetry.update();
    }
}
