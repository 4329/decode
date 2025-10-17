package org.firstinspires.ftc.teamcode.util;

public enum SpindexPos {
    INTAKE_ONE(0),
    INTAKE_TWO(0.9),
    INTAKE_THREE(0.4),
    SHOOT_ONE(0.5),
    SHOOT_TWO(-0.5),
    SHOOT_THREE(0.95);

    private double value;
    SpindexPos(double v){
        this.value = v;
    }

    public double getValue() {
        return this.value;
    }
}
