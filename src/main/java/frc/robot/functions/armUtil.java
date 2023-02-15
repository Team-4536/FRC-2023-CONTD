package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.IntakeData;

public class armUtil {

    public static void toggleSolenoid(IntakeData pData){

        
        pData.armSolenoid.toggle();
        
    }

    public static void retractAll(IntakeData pData){
        
        pData.armSolenoid.set(Value.kReverse);

    }

    public static void extendAll(IntakeData pData){
        
        pData.armSolenoid.set(Value.kForward);

    }
    
}
