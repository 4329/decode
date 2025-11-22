package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Alliance;

public class BlinkinSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    private final Alliance alliance;
    private RevBlinkinLedDriver blinky;
    private RevBlinkinLedDriver.BlinkinPattern currentPattern;
    public BlinkinSubsystem(HardwareMap hardwareMap, Telemetry telemetry, Alliance alliance) {
        this.blinky = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        this.telemetry = telemetry;
        this.alliance = alliance;
        init();
    }

    private void init() {
        if(alliance.equals(Alliance.BLUE)){
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE;
        }
        else{
            currentPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE;
        }
        blinky.setPattern(currentPattern);
    }
}
