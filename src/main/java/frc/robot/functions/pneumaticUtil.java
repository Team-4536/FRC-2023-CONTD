package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class pneumaticUtil {


    public static void toggleSolenoid(DoubleSolenoid s){
        runSolenoid(s, !getStatus(s));
    }

    public static boolean getStatus(DoubleSolenoid s) {
        return s.get() == Value.kForward;
    }

    public static void runSolenoid(DoubleSolenoid s, boolean extended){
        s.set(extended?Value.kForward : Value.kReverse);
    }

}

