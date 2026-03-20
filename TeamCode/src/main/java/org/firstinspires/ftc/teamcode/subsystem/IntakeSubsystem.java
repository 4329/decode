package org.firstinspires.ftc.teamcode.subsystem;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;


import org.firstinspires.ftc.teamcode.util.SpindexPos;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo inTakeMotor;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        this.inTakeMotor = hardwareMap.get(CRServo.class,"intake");
    }
    public void on( ) {
inTakeMotor.setPower(1);
    }
    public void off( ) {
inTakeMotor.setPower(0);
    }
}
