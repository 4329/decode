package org.firstinspires.ftc.teamcode.util;

public enum SpindexPos {
    INTAKE_ONE(0.6),
    INTAKE_TWO(0.13),
    INTAKE_THREE(1),
    SHOOT_ONE(0.13),
    SHOOT_TWO(1),
    SHOOT_THREE(0.6);

    private double value;
    SpindexPos(double v){
        this.value = v;
    }

    public double getValue() {
        return this.value;
    }
}
