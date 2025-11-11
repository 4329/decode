package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Motor test")
public class MotorTest extends LinearOpMode {
    private DcMotorEx motory;

    @Override
    public void runOpMode() throws InterruptedException {
        motory = hardwareMap.get(DcMotorEx.class, "motorTest");
        motory.setDirection(DcMotorSimple.Direction.FORWARD);
        motory.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int startingPos = motory.getCurrentPosition();

        int timesThroughLoop = 100;
        int loopCount = 0;

        waitForStart();
        boolean velocityBueno = false;
        boolean positionBueno = false;

        while(opModeIsActive() && loopCount < timesThroughLoop) {
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
            loopCount++;
        }

        motory.setVelocity(0);
        motory.setPower(0);
        telemetry.addData("runnin", "nope");
        telemetry.addData("position all good?", positionBueno);
        telemetry.addData("velocity all good?", velocityBueno);
        telemetry.speak(velocityBueno && positionBueno ? "super duper" : "motor is broke");
        telemetry.update();
    }
}
