package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TurretData {
    
    public CANSparkMax turretMotor = new CANSparkMax(5, MotorType.kBrushed);
    
    private DigitalInput leftBound = new DigitalInput(0);
    private DigitalInput rightBound = new DigitalInput(1);


        public void sendTelemetry(){

            telemetryUtil.put("Turret Motor", turretMotor.get(), Tabs.ROBOT);
            
            telemetryUtil.put("Left Bound Limit Switch", leftBound.get(), Tabs.ROBOT);
            telemetryUtil.put("Right Bound Limit Switch", rightBound.get(), Tabs.ROBOT);

        }

}
