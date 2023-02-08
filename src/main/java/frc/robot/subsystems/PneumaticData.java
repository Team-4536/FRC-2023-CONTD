package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class PneumaticData {
    
    public static PneumaticsControlModule pcm = new PneumaticsControlModule(0);

    public DoubleSolenoid brakeSolenoid = pcm.makeDoubleSolenoid(0, 0);


    public void sendTelemetry(){

        telemetryUtil.put("Brake is Extended", brakeSolenoid.get() == DoubleSolenoid.Value.kForward,Tabs.ROBOT);

        
    }
    
}
