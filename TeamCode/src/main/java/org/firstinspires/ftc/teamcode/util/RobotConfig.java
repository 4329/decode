package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RobotConfig {
    public static double REGULAR_DRIVE_DIVISOR = 2.0;
    public static double SLOW_DRIVE_DIVISOR = 5.0;

    public static int ICON_SIZE = 10;
    public static int ORIGIN_X = 0;
    public static int ORIGIN_Y = 0;

    public static double DASHBOARD_SPIN = 0;
    public static double SHOOTER_P = 0.001;
    public static double SHOOTER_I = 0.005;
    // maybe increase
    public static double SHOOTER_D = 0.0002;
    public static double SHOOTER_FF_S = 0.01;
    public static double SHOOTER_FF_V = 0.00053;
    public static double PUSHY_UP = 0.38;
    public static double PUSHY_DOWN = 0.78;
    public static double SHOOTER_FAR_GOAL = 1700;
    public static double SHOOTER_CLOSE_GOAL = 1450;
    public static double EXPECTED_BATTERY_VOLTAGE = 13.0;kk
}