package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Constants.MechanismInfo;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TurretData {
    
    public CANSparkMax turretMotor = new CANSparkMax(MechanismInfo.TURRET_ID, MechanismInfo.TURRET_MOTOR_TYPE);
    
    public DigitalInput cwBound = new DigitalInput(Constants.CLOCKWISE_BOUND_ID);
    public DigitalInput ccwBound = new DigitalInput(Constants.COUNTERCLOCKWISE_BOUND_ID);


        public void sendTelemetry(){

            telemetryUtil.put("Turret Motor", turretMotor.get(), Tabs.ROBOT);
            
            telemetryUtil.put("Clockwise Limit Switch", cwBound.get(), Tabs.ROBOT);
            telemetryUtil.put("Counter Clockwise Limit Switch", ccwBound.get(), Tabs.ROBOT);

        }

}
