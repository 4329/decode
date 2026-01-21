package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.SpindexerMode;

public class BlinkinSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private final Alliance alliance;
    private RevBlinkinLedDriver blinky;
    private RevBlinkinLedDriver.BlinkinPattern currentPattern;
    private SpindexerMode newMode;
    private boolean robotOriented;

    public BlinkinSubsystem(HardwareMap hardwareMap, Telemetry telemetry, Alliance alliance) {
        this.blinky = hardwareMap.get(RevBlinkinLedDriver.class, "blinky");
        this.telemetry = telemetry;
        this.alliance = alliance;
        AllianceColor();
    }

    private void AllianceColor() {
        if(alliance.equals(Alliance.BLUE)){
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE;
        }
        else{
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE;
        }
        blinky.setPattern(currentPattern);
    }
    public void changeDriveMode(boolean robotOriented){
        this.robotOriented = robotOriented;
        if (SpindexerMode.INTAKE.equals(newMode)) {
            if (robotOriented) {
                currentPattern = RevBlinkinLedDriver.BlinkinPattern.CP1_LIGHT_CHASE;
            }
            else {
                currentPattern = RevBlinkinLedDriver.BlinkinPattern.CP2_LIGHT_CHASE;
            }
            blinky.setPattern(currentPattern);
        }
    }
    public void changeMode(SpindexerMode newMode) {
        this.newMode = newMode;
        if (SpindexerMode.UNKNOWN.equals(newMode)) {
            AllianceColor();
        }

        else if (SpindexerMode.SHOOT.equals(newMode)) {
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.LARSON_SCANNER_GRAY;
        }
        else {
            changeDriveMode(robotOriented);
        }

        blinky.setPattern(currentPattern);

    }
    public void tagInSight(boolean geoffery) {
        if (geoffery) {
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE;
            blinky.setPattern(currentPattern);
        }
        else {
            changeMode(newMode);
        }
    }
}