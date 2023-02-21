package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.IntakeData;

public class armUtil {

    public static void toggleSolenoid(IntakeData pData){

        pData.grabberSolenoid.toggle();
    }

    public static void retractAll(IntakeData pData){

        pData.grabberSolenoid.set(Value.kReverse);

    }

    public static void extendAll(IntakeData pData){
        pData.grabberSolenoid.set(Value.kForward);

    }

    public static void runCondition(IntakeData pData, boolean forwardCondition){

        if(forwardCondition){pData.grabberSolenoid.set(Value.kForward);}
        else {pData.grabberSolenoid.set(Value.kReverse);}

    }
}
