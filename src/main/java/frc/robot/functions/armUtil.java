package frc.robot.functions;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.IntakeData;

public class armUtil {

    public void toggleSolenoid(IntakeData pData){

        
        pData.armSolenoid.toggle();
        
    }

    public void retractAll(IntakeData pData){
        
        pData.armSolenoid.set(Value.kReverse);
        pData.extendMotor.set(0);
        

    }

    public void activate(IntakeData pData, double speed){

        if (isDeployed(pData)){pData.extendMotor.set(speed);}
        else pData.extendMotor.set(0);
    
    }

    public boolean isDeployed(IntakeData pData){

        return pData.armSolenoid.get() == DoubleSolenoid.Value.kForward;

    }

    public void extendAll(IntakeData pData){
        
        pData.armSolenoid.set(Value.kForward);

    }


    
}
