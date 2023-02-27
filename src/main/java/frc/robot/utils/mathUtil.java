package frc.robot.utils;

public final class mathUtil {


    public static double clamp(double x, double h, double l) {
        if(x > h) { x = h; }
        if(x < l) { x = l; }

        return x;
    };

    public static double clampLen(double x, double l) {
        return clamp(x, l, -1);
    }



}
