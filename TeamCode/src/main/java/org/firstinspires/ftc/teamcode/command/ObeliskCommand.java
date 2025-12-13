package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.RobotState;


public class ObeliskCommand extends CommandBase{
    private final LimeLightSubsystem limeLightSubsystem;
    private final Telemetry telemetry;
    private final RobotState robotState;
    private int tagAttempt;
    private final int MAX_TRIES = 10;
    private int tag;
    private ElapsedTime timer = new ElapsedTime();

    public ObeliskCommand(LimeLightSubsystem limeLightSubsystem, Telemetry telemetry, RobotState robotState) {
        this.limeLightSubsystem = limeLightSubsystem;
        this.telemetry = telemetry;
        this.robotState = robotState;
    }

    @Override
    public void initialize() {
    limeLightSubsystem.pipelineObelisk();
    tagAttempt = 0;
    timer.reset();
    }

    @Override
    public void end(boolean interrupted) {
    limeLightSubsystem.pipelineAlliance();
       }

    @Override
    public boolean isFinished() {
        if (tag > 0) {
            return true;
        } else if (tagAttempt == MAX_TRIES) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void execute() {
        if (timer.milliseconds()>=60) {
            tag = limeLightSubsystem.getTagID();
            if (tag > 0) {
                robotState.wehadID(tag);
                Log.i("oby", "tag " + tag);
            } else {
                tagAttempt++;
            }
        }
    }
}