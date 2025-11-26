package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Motor test")
public class MotorTest extends LinearOpMode {
    private DcMotorEx motory;
    private Deadline testDuration = new Deadline(5, TimeUnit.SECONDS);
    @Override
    public void runOpMode() throws InterruptedException {
        motory = hardwareMap.get(DcMotorEx.class, "motorTest");
        motory.setDirection(DcMotorSimple.Direction.FORWARD);
        motory.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int startingPos = motory.getCurrentPosition();

        waitForStart();
        testDuration.reset();

        boolean velocityBueno = false;
        boolean positionBueno = false;

        while(opModeIsActive() && !testDuration.hasExpired()) {
            motory.setVelocity(-500);
            int curPos = motory.getCurrentPosition();
            double velocity = motory.getVelocity();
            if (velocity > 0 && !velocityBueno) {
                velocityBueno = true;
            }
            if (Math.abs(startingPos - curPos) > 0 && !positionBueno) {
                positionBueno = true;
            }
            telemetry.addData("runnin", "oh yeah");
            telemetry.addData("velocity", velocity);
            telemetry.addData("starting pos", startingPos);
            telemetry.addData("current pos", curPos);
            telemetry.update();
        }

        motory.setVelocity(0);
        motory.setPower(0);
        telemetry.addData("runnin", "nope");
        telemetry.addData("position all good?", positionBueno);
        telemetry.addData("velocity all good?", velocityBueno);
        telemetry.speak(velocityBueno && positionBueno ? "super duper" : "motor is broken");
        telemetry.update();
        testDuration.reset();
        while (!testDuration.hasExpired()) {
            telemetry.update();
        }
    }
}
