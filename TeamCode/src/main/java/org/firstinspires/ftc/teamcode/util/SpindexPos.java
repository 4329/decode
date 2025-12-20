package org.firstinspires.ftc.teamcode.util;

public enum SpindexPos {
    INTAKE_ONE(0),
    INTAKE_TWO(0.9),
    INTAKE_THREE(0.45),
    SHOOT_ONE(0.9),
    SHOOT_TWO(0.45),
    SHOOT_THREE(0);

    private double value;
    SpindexPos(double v){
        this.value = v;
    }

    public double getValue() {
        return this.value;
    }
}
