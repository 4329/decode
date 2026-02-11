package org.firstinspires.ftc.teamcode.command;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;

public class LineAlwaysStuffUpCommand extends LineStuffUpCommand {
    public LineAlwaysStuffUpCommand(LimeLightSubsystem limeLightSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem) {
        super(limeLightSubsystem, mecanumDriveSubsystem);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
