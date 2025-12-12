package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;

public class ObeliskCommand extends CommandBase{
    private final LimeLightSubsystem limeLightSubsystem;
    private final Telemetry telemetry;
    public ObeliskCommand(LimeLightSubsystem limeLightSubsystem, Telemetry telemetry) {
        this.limeLightSubsystem = limeLightSubsystem;
        this.telemetry = telemetry;
    }

    @Override
    public void initialize() {
    limeLightSubsystem.pipelineObelisk();

    }

    @Override
    public void end(boolean interrupted) {
    limeLightSubsystem.pipelineAlliance();
       }

    @Override
    public boolean isFinished() {

    }

    @Override
    public void execute() {
        super.execute();
    }
}