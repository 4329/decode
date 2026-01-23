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
            changePattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE);
        }
        else{
            changePattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE);
        }
    }
    public void changeDriveMode(boolean robotOriented){
        this.robotOriented = robotOriented;
        if (SpindexerMode.INTAKE.equals(newMode)) {
            if (robotOriented) {
                changePattern(RevBlinkinLedDriver.BlinkinPattern.CP1_LIGHT_CHASE);
            }
            else {
                changePattern(RevBlinkinLedDriver.BlinkinPattern.CP2_LIGHT_CHASE);
            }
        }
    }
    public void changeMode(SpindexerMode newMode) {
        this.newMode = newMode;
        if (SpindexerMode.UNKNOWN.equals(newMode)) {
            AllianceColor();
        }

        else if (SpindexerMode.SHOOT.equals(newMode)) {
            changePattern(RevBlinkinLedDriver.BlinkinPattern.LARSON_SCANNER_GRAY);
        }
        else {
            changeDriveMode(robotOriented);
        }
    }
    public void tagInSight(boolean geoffery) {
        if (geoffery) {
            changePattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE);
        }
        else {
            changeMode(newMode);
        }
    }
    public void celebration() {
        changePattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
    }
    private void changePattern(RevBlinkinLedDriver.BlinkinPattern crocodile) {
        currentPattern = crocodile;
        blinky.setPattern(crocodile);
    }

}