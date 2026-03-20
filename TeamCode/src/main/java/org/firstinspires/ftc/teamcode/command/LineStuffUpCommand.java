package org.firstinspires.ftc.teamcode.command;


import android.util.Log;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.util.FrcPidController;

public class LineStuffUpCommand extends CommandBase {
    private final LimeLightSubsystem limeLightSubsystem;
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final FrcPidController frcPid;


    public LineStuffUpCommand(LimeLightSubsystem limeLightSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem) {
        this.limeLightSubsystem = limeLightSubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.frcPid = new FrcPidController(0.025, .00, 0.00125); //kd was 0.000075
        addRequirements(limeLightSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public void initialize() {
        frcPid.setSetpoint(0);
        frcPid.setTolerance(0.4);

   }

    @Override
    public void execute() {
        if(limeLightSubsystem.isTargetVisible()) {
            double tx = limeLightSubsystem.getTurboXylophone();
            double output = frcPid.calculate(tx);
            output = Range.clip(output, -.7, .7);
            mecanumDriveSubsystem.drive(0, 0, -output);
            Log.i("ll-limeup", "tx, output " + "(" + tx + ", " + output + ")");
        }
        else {
            mecanumDriveSubsystem.stop();
            Log.i("ll-limeup", "me see no target");
        }
      }


    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
        Log.i("ll-limeup", "all done");
}

    @Override
    public boolean isFinished() {
        Log.i("ll-limeup", String.format("are we there yet? %b", frcPid.atSetpoint()));
        return frcPid.atSetpoint();
    }
}
