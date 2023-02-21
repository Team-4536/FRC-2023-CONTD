package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class PneumaticData {


    public static PneumaticsControlModule pcm = new PneumaticsControlModule(Hardware.PCM_ID_PORT);

    public DoubleSolenoid brakeSolenoid = pcm.makeDoubleSolenoid(Hardware.BRAKE_SOLENOID_FORWARD_PORT,
        Hardware.BRAKE_SOLENOID_REVERSE_PORT);

    public static boolean status = false;





    public void sendTelemetry(){

        telemetryUtil.put("Brake is Extended", brakeSolenoid.get() == DoubleSolenoid.Value.kForward, Tabs.ROBOT);
        telemetryUtil.put("Brake is Extended", brakeSolenoid.get() == DoubleSolenoid.Value.kForward, Tabs.DRIVER);
    }
}
