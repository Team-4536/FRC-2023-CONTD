package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.Constants;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class PneumaticData {
    
    public static PneumaticsControlModule pcm = new PneumaticsControlModule(Constants.PCM_ID_PORT);

    public DoubleSolenoid brakeSolenoid = pcm.makeDoubleSolenoid(Constants.BRAKE_SOLENOID_FORWARD_PORT, 
    Constants.BRAKE_SOLENOID_REVERSE_PORT);


    public void sendTelemetry(){

        telemetryUtil.put("Brake is Extended", brakeSolenoid.get() == DoubleSolenoid.Value.kForward,Tabs.ROBOT);

        
    }
    
}
