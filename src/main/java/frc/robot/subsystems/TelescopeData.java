package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.MechanismInfo;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TelescopeData {

    public CANSparkMax liftMotor = new CANSparkMax(MechanismInfo.ARM_LIFT_ID, MechanismInfo.ARM_MOTOR_TYPE);
    public CANSparkMax extendMotor = new CANSparkMax(MechanismInfo.ARM_EXTEND_ID, MechanismInfo.ARM_MOTOR_TYPE);

    public DigitalInput upBound = new DigitalInput(2);
    public DigitalInput lowBound = new DigitalInput(3);

    public void sendTelemetry(){

        telemetryUtil.put("Upper Limit", upBound.get(), Tabs.ROBOT);
        telemetryUtil.put("Lower Limit", lowBound.get(), Tabs.ROBOT);

    }
    
}
