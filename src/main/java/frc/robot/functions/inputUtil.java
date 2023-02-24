package frc.robot.functions;

import frc.robot.constants.ControlSettings;

public class inputUtil {

    public static double mapInput(double input, double inputHigh, double inputLow, double outHigh, double outLow) {

        return ((input-inputLow) / (inputHigh - inputLow)) * (outHigh - outLow) + outLow;
    }

    public static double deadzoneAxis(double input, double minLen) {

        double r = (Math.abs(input) < minLen? 0 : Math.abs(input));

        if(r == 0) { return 0; }

        r = mapInput(r, 1, minLen, 1, 0);
        r *= Math.signum(input);
        return r;
    }

    public static double scalePoints(double low, double high, double newLow, double newHigh, double value){

        double range = high - low;
        double newRange = newHigh - newLow;

        double val2 = (value - low)/(range) * newRange + newLow;

        return val2;

    }

    public static double deadzoneStick(double x) {
        return deadzoneAxis(x, ControlSettings.MOVEMENT_DEADZONE);
    }

}
