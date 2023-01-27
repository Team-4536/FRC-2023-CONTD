package frc.robot.subsystems;

import java.util.concurrent.CancellationException;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretData {
    
    public CANSparkMax turretMotor = new CANSparkMax(5, MotorType.kBrushed);
    
    private DigitalInput leftBoundLimitSwitch = new DigitalInput(0);
    private DigitalInput rightBoundLimitSwitch = new DigitalInput(0);


        public void sendTelemetry(){

            SmartDashboard.putNumber("Turret Motor", turretMotor.get());
            SmartDashboard.putBoolean("Left Bound Limit Switch", leftBoundLimitSwitch.get());
            SmartDashboard.putBoolean("Right Bound Limit Switch", rightBoundLimitSwitch.get());

        }

}
