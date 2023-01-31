package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.functions.telemetryUtil;

public class PneumaticData {
    
    public PneumaticsControlModule pcm = new PneumaticsControlModule(0);

    public DoubleSolenoid leftBackSol = pcm.makeDoubleSolenoid(0, 0);
    public DoubleSolenoid rightBackSol = pcm.makeDoubleSolenoid(0, 0);
    public DoubleSolenoid leftFrontSol = pcm.makeDoubleSolenoid(0, 0);
    public DoubleSolenoid rightFrontSol = pcm.makeDoubleSolenoid(0, 0);


    public void sendTelemetry(){

        
    }
    
}
