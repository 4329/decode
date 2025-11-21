package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.ParallelDeadlineGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PushyMcPushermanSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpindexerSubsystem;

public class AutoCommandFactory {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final ImuSubsystem imuSubsystem;
    private final Telemetry telemetry;
    private final LimeLightSubsystem limeLightSubsystem;
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final SpindexerSubsystem spindexerSubsystem;

    public AutoCommandFactory(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem, Telemetry telemetry, LimeLightSubsystem limeLightSubsystem, PushyMcPushermanSubsystem pushyMcPushermanSubsystem, ShooterSubsystem shooterSubsystem, SpindexerSubsystem spindexerSubsystem) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.imuSubsystem = imuSubsystem;
        this.telemetry = telemetry;
        this.limeLightSubsystem = limeLightSubsystem;
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.spindexerSubsystem = spindexerSubsystem;
    }

    public Command exampleCommand() {
        return new SequentialCommandGroup(
                forward(11, 0),
                new TurnToHeadingCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 45),
                forward(5, 45)
        );
    }

    private Command backUp(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0.35, 0, 0, inches);
    }

    private Command forward(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.35, 0, 0, inches);
    }

    private Command slowForward(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.2, 0, 0, inches);
    }

    private Command lMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.3, inches);
    }

    private Command lFastMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.7, inches);
    }

    private Command rMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .21, inches);
    }

    private Command rFastMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .7, inches);
    }

    public Command scoreThingsPlease() {
        return new ParallelDeadlineGroup(new SequentialCommandGroup(
                new LineStuffUpCommand(limeLightSubsystem, mecanumDriveSubsystem),
                new ReadyShootCommand(shooterSubsystem, telemetry),
                new UnInstantCommand(() -> pushyMcPushermanSubsystem.up()),
                new WaitCommand(500),
                new UnInstantCommand(() -> pushyMcPushermanSubsystem.down())
                //and thank you
        ),
                new ShootCommand(shooterSubsystem));
    }
}