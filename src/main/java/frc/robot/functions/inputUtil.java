package frc.robot.functions;

public class inputUtil {

    public static double mapInput(double input, double inputHigh, double inputLow, double outHigh, double outLow) {

        return ((input-inputLow) / (inputHigh - inputLow)) * (outHigh - outLow) + outLow;
    }

    public static double deadzoneAxis(double input, double minLen) {

        double r = (Math.abs(input) < minLen? 0 : Math.abs(input));
        r = mapInput(r, 1, minLen, 1, -1);
        r *= Math.signum(input);
        return r;
    }

}
