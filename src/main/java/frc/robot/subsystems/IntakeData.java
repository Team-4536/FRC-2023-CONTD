package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.Constants;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class IntakeData {

    public PneumaticsControlModule pcm = PneumaticData.pcm;

    public DoubleSolenoid armSolenoid = pcm.makeDoubleSolenoid(Constants.ARM_SOLENOID_FORWARD_PORT, 
    Constants.ARM_SOLENOID_REVERSE_PORT);

    public void sendTelemetry(){

        telemetryUtil.put("Arm is Extended", armSolenoid.get() == DoubleSolenoid.Value.kForward,Tabs.ROBOT); 

    }
    
}
