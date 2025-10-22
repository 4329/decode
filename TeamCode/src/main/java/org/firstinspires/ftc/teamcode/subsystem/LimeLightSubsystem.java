package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimeLightSubsystem extends SubsystemBase {
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private final Limelight3A limelight;
    private double tubroXylophone;
    private Pose3D botpose;

    private boolean targetVisible = false;

    private ElapsedTime timeSinceTag = new ElapsedTime();
    public LimeLightSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        emit();
    }
    private void emit(){
        limelight.pipelineSwitch(0);
        limelight.start();
        timeSinceTag.reset();
    }

    @Override
    public void periodic() {
        LLStatus status = limelight.getStatus();
        telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                status.getTemp(), status.getCpu(), (int) status.getFps());
        LLResult result = limelight.getLatestResult();
        if (result.isValid()) {
             tubroXylophone = result.getTx();
             botpose = result.getBotpose();
             targetVisible = true;
             timeSinceTag.reset();
        }
        else {
            targetVisible = false;
        }
        telemetry.addData("tx", result.getTx());
        telemetry.addData("Botpose", botpose.toString());
        telemetry.addData("targetVisible", targetVisible);
    }

    public double getTimeSinceTag() {
        return timeSinceTag.milliseconds();
    }

    public boolean isTargetVisible() {
        return targetVisible;
    }

    public Pose3D getBotpose() {
        return botpose;
    }

    public double getTubroXylophone() {
        return tubroXylophone;
    }
}
