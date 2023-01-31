package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.PneumaticData;

public class pneumaticUtil {
    


    public void toggleSolenoid(PneumaticData pData){

        
            pData.leftBackSol.toggle();
            pData.leftFrontSol.toggle();
            pData.rightBackSol.toggle(); 
            pData.rightFrontSol.toggle();
        
    }

    public void retractAll(PneumaticData pData){
        
        pData.leftBackSol.set(Value.kReverse);
        pData.leftFrontSol.set(Value.kReverse);
        pData.rightBackSol.set(Value.kReverse);
        pData.rightFrontSol.set(Value.kReverse);

    }

    public void extendAll(PneumaticData pData){
        
        pData.leftBackSol.set(Value.kForward);
        pData.leftFrontSol.set(Value.kForward);
        pData.rightBackSol.set(Value.kForward);
        pData.rightFrontSol.set(Value.kForward);

    }
    

    }

