package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class IntakeData {

    public PneumaticsControlModule pcm = new PneumaticsControlModule(0);

    public DoubleSolenoid armSolenoid = pcm.makeDoubleSolenoid(0, 0);

    public void sendTelemetry(){

        telemetryUtil.put("Arm is Extended", armSolenoid.get() == DoubleSolenoid.Value.kForward,Tabs.ROBOT); 

    }
    
}
