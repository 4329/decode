package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.InitializeNavxCommand;
import org.firstinspires.ftc.teamcode.command.TurnToHeadingCommand;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;

@Autonomous(name = "ExampleAuto", group = "2")
public class ExampleAuto extends CommandOpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private ImuSubsystem imuSubsystem;
    private TurnToHeadingCommand turnToHeadingCommand;

    @Override
    public void initialize() {
        telemetry.speak("running " + getClass().getSimpleName());

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
        AutoCommandFactory factory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry);
        SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
            new InitializeNavxCommand(imuSubsystem, telemetry).withTimeout(1000),
            factory.exampleCommand()
        );

        register(telemetryUpdateSubsystem, imuSubsystem);
        schedule(autoCommandGroup);
    }
}
