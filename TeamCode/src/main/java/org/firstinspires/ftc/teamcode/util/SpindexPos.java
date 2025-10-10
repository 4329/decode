package org.firstinspires.ftc.teamcode.util;

public enum SpindexPos {
    INTAKE_ONE(.5),
    INTAKE_TWO(0),
    INTAKE_THREE(.9),
    //shooter needs to be offset
    SHOOT_ONE(0),
    SHOOT_TWO(0.67),
    SHOOT_THREE(0.23);

    private double value;
    SpindexPos(double v){
        this.value = v;
    }

    public double getValue() {
        return this.value;
    }
}
