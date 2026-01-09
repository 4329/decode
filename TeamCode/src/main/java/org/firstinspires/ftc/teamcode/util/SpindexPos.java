package org.firstinspires.ftc.teamcode.util;

public enum SpindexPos {
    INTAKE_ONE(0.13),
    INTAKE_TWO(0.57),
    INTAKE_THREE(1),
    SHOOT_ONE(1),
    SHOOT_TWO(0.57),
    SHOOT_THREE(0.13);

    private double value;
    SpindexPos(double v){
        this.value = v;
    }

    public double getValue() {
        return this.value;
    }
}