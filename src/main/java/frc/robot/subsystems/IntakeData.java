package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import frc.robot.Constants;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class IntakeData {

    public PneumaticsControlModule pcm = new PneumaticsControlModule(0);

    public final CANSparkMax extendMotor = new CANSparkMax(Constants.EXTEND_INTAKE_PORT, MotorType.kBrushless);

    public DoubleSolenoid armSolenoid = pcm.makeDoubleSolenoid(0, 0);

    public void sendTelemetry(){

        telemetryUtil.put("Arm is Extended", armSolenoid.get() == DoubleSolenoid.Value.kForward,Tabs.ROBOT); 

    }
    
}
