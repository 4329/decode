package org.firstinspires.ftc.teamcode.command;


import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;

public class LineStuffUpCommand extends CommandBase {
    private final LimeLightSubsystem limeLightSubsystem;
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private double tolerance = .1;

    public LineStuffUpCommand(LimeLightSubsystem limeLightSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem) {
        this.limeLightSubsystem = limeLightSubsystem;
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        addRequirements(limeLightSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        if(limeLightSubsystem. isTargetVisible()){
            double Tx = limeLightSubsystem.getTurboXylophone();
            if(Math.abs(Tx)>tolerance){
                mecanumDriveSubsystem.drive( 0,0, Tx < 0 ? -.075 : .075);
            }  else{
                mecanumDriveSubsystem.stop();

            }
        }
        else{
            mecanumDriveSubsystem.stop();

        }

      }


    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        boolean isLinedUp = Math.abs(limeLightSubsystem.getTurboXylophone())<= tolerance;
        boolean cantSeeTag = !limeLightSubsystem.isTargetVisible()&& limeLightSubsystem.getTimeSinceTag()>150;
        Log.i("ll-lineup", String.format("isLinedUp / cantSeeTag:  %b / %b", isLinedUp, cantSeeTag));
        return isLinedUp || cantSeeTag;
    }
}
