package org.firstinspires.ftc.teamcode.util;

public enum Alliance {
    RED(-1, 2),
    BLUE (1, 1);

    public final int value;
    public final int pipeline;

    private Alliance(int value, int pipeline){
        this.value=value;
        this.pipeline=pipeline;
    }
}
