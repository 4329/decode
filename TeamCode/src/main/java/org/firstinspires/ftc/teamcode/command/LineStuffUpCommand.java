package org.firstinspires.ftc.teamcode.command;


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
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        double Tx = limeLightSubsystem.getTubroXylophone();
      if(Math.abs(Tx)>tolerance){
          mecanumDriveSubsystem.drive( 0,0,Tx<0 ? .1:-.1);
      }
    }

    @Override
    public void end(boolean interrupted) {
        mecanumDriveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
