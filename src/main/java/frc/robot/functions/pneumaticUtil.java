package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.PneumaticData;

public class pneumaticUtil {
    


    public void toggleSolenoid(PneumaticData pData){

        
            pData.breakSolenoid.toggle();
        
    }

    public void retractAll(PneumaticData pData){
        
        pData.breakSolenoid.set(Value.kReverse);

    }

    public void extendAll(PneumaticData pData){
        
        pData.breakSolenoid.set(Value.kForward);

    }
    

    }

