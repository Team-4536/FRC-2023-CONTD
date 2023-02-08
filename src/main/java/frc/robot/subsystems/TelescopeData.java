package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TelescopeData {

    public CANSparkMax liftMotor = new CANSparkMax(6, MotorType.kBrushed);
    
}
