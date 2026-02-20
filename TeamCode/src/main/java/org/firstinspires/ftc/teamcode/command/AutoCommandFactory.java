package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.DeadlinableSequentialCommandGroup;
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
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;

public class AutoCommandFactory {
    private final MecanumDriveSubsystem mecanumDriveSubsystem;
    private final ImuSubsystem imuSubsystem;
    private final Telemetry telemetry;
    private final LimeLightSubsystem limeLightSubsystem;
    private final PushyMcPushermanSubsystem pushyMcPushermanSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final SpindexerSubsystem spindexerSubsystem;
    private final Alliance alliance;
    private final VoltageSubsystem voltageSubsystem;

    public AutoCommandFactory(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem, Telemetry telemetry, LimeLightSubsystem limeLightSubsystem, PushyMcPushermanSubsystem pushyMcPushermanSubsystem, ShooterSubsystem shooterSubsystem, SpindexerSubsystem spindexerSubsystem, Alliance alliance, VoltageSubsystem voltageSubsystem) {
        this.mecanumDriveSubsystem = mecanumDriveSubsystem;
        this.imuSubsystem = imuSubsystem;
        this.telemetry = telemetry;
        this.limeLightSubsystem = limeLightSubsystem;
        this.pushyMcPushermanSubsystem = pushyMcPushermanSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.spindexerSubsystem = spindexerSubsystem;
        this.alliance = alliance;
        this.voltageSubsystem = voltageSubsystem;
    }

    public Command exampleCommand() {
        return new SequentialCommandGroup(
                forward(11, 0),
                new TurnToHeadingCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 45),
                forward(5, 45)
        );
    }

    private Command backUp(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.35, 0, 0, inches);
    }

    public Command forward(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0.35, 0, 0, inches);
    }
    public Command allianceForward(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0.35*alliance.value, 0, 0, inches);
    }

    private Command slowForward(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.2, 0, 0, inches);
    }

    public Command lMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.21, inches);
    }

     public Command lFastMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.7, inches);
    }

    public Command rMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .21, inches);
    }

    private Command rFastMove(double inches, double heading) {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .7, inches);
    }

    public Command scoreThingsPlease(boolean stopAfterShot) {
        return new SequentialCommandGroup(
                new ParallelDeadlineGroup(
                    new DeadlinableSequentialCommandGroup(
                        new LineStuffUpCommand(limeLightSubsystem, mecanumDriveSubsystem),
                        new ReadyShootCommand(shooterSubsystem, limeLightSubsystem, telemetry),
                        new UnInstantCommand(() -> pushyMcPushermanSubsystem.up()),
                        new WaitCommand(500)
                        //and thank you
                    ),
                    new ShootCommand(shooterSubsystem, limeLightSubsystem, stopAfterShot, voltageSubsystem)
                ).withTimeout(5000),
                new UnInstantCommand(() -> pushyMcPushermanSubsystem.down()),
                new WaitCommand(500)
        );
    }
    public Command oneShot() {
        return new SequentialCommandGroup(
                new UnInstantCommand(() -> pushyMcPushermanSubsystem.up()),
                new WaitCommand(700),
                new UnInstantCommand(() -> pushyMcPushermanSubsystem.down()),
                new WaitCommand(700)
                );
    }
    public Command tripleShotEspresso() {
        return new ParallelDeadlineGroup(
                new DeadlinableSequentialCommandGroup(
                    new ReadyShootCommand(shooterSubsystem, limeLightSubsystem,telemetry),
                    oneShot(),
                    new SpindexerCommand(spindexerSubsystem, 1),
                    oneShot(),
                    new SpindexerCommand(spindexerSubsystem, 1),
                    oneShot()
                ),
                new ShootCommand(shooterSubsystem, limeLightSubsystem, true, voltageSubsystem),
                new LineAlwaysStuffUpCommand(limeLightSubsystem, mecanumDriveSubsystem),
                new IsFunishootedCommand(pushyMcPushermanSubsystem)
                );
    }

    public Command strafeToYourLou() {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, 0, 0, 0, .3, 26);
    }

    public Command moveAwayFromYourLou() {
        return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, 0, .3*alliance.value, 0,0 , 40);
    }
    
}