package org.firstinspires.ftc.teamcode.subsystem;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotConfig;

import java.util.function.Consumer;

public class LimeLightSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private final Limelight3A limelight;
    private final Alliance alliance;
    private final Consumer<Boolean> tagInSightChanger;
    private double turboXylophone;
    private double turboYogurt;
    private Pose3D botpose;
    private int tagID = -123;
    private boolean targetVisible = false;
    private final double DIVIDING_POINT = 13.2;

    private ElapsedTime timeSinceTag = new ElapsedTime();

    public LimeLightSubsystem(HardwareMap hardwareMap, Telemetry telemetry, Alliance alliance, Consumer<Boolean> tagInSightChanger) {
        this.telemetry = telemetry;
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        this.alliance = alliance;
        this.tagInSightChanger = tagInSightChanger;
        init();
    }
    private void init(){

        limelight.pipelineSwitch(alliance.pipeline);
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
             turboXylophone = result.getTx();
             turboYogurt = result.getTy();
             botpose = result.getBotpose();
             if (result.getFiducialResults() != null && !result.getFiducialResults().isEmpty()){
                 tagID = result.getFiducialResults().get(0).getFiducialId();
                for (LLResultTypes.FiducialResult fr: result.getFiducialResults()){
                    Log.i ("tagID", "FrenchDoggies " +fr.getFiducialId());
                }
             }
             targetVisible = true;
             timeSinceTag.reset();
        }
        else {
            targetVisible = false;
            tagID = -123;
        }
        tagInSightChanger.accept(targetVisible);
        Log.i("LL-botpose", botpose + "");
        Log.i("LL-vis", targetVisible + "");
        Log.i("LL-tx", turboXylophone + "");
        Log.i("LL-ty", turboYogurt + "");
        telemetry.addData("tx", result.getTx());
        telemetry.addData("ty", result.getTy());
        telemetry.addData("Botpose", (botpose != null) ? botpose.toString() : "blech");
        telemetry.addData("TargetVisible", targetVisible);
        telemetry.addData("Tag", tagID);

        /*
        if (botpose != null) {
            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
         */
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

    public double getTurboXylophone() {
        return turboXylophone;
    }

    public double getTurboYogurt() {
        return turboYogurt;
    }

    public double getTargetVelocity() {
        if (turboYogurt < DIVIDING_POINT) {
            return RobotConfig.SHOOTER_FAR_GOAL;
        }
        else {
            return RobotConfig.SHOOTER_CLOSE_GOAL;
        }
    }

    public void pipelineObelisk() {
        limelight.pipelineSwitch(0);

    }

    public void pipelineAlliance() {
        limelight.pipelineSwitch(alliance.pipeline);
    }

    public int getTagID() {
        return tagID;
    }
}
