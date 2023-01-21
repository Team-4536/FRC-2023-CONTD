package frc.robot.functions;

public class inputUtil {

    public static double mapInput(double input, double inputHigh, double inputLow, double outLow, double outHigh) {

        return ((input-inputLow) / (inputHigh - inputLow)) * (outHigh - outLow) + outLow;
    }

    public static double deadzoneAxis(double input, double minLen) {

        double r = (Math.abs(input) < minLen? 0 : Math.abs(input)) * Math.signum(input);
        return r;
    }

}
