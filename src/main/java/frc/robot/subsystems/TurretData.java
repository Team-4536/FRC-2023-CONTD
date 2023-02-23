package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TurretData {

    public CANSparkMax turretMotor = new CANSparkMax(Hardware.TURRET_ID, Hardware.TURRET_MOTOR_TYPE);

    public RelativeEncoder turretEncoder = turretMotor.getEncoder();

    public DigitalInput cwBound = new DigitalInput(Hardware.CLOCKWISE_BOUND_ID);
    public DigitalInput ccwBound = new DigitalInput(Hardware.COUNTERCLOCKWISE_BOUND_ID);




    public TurretData() {
        this.turretMotor.setInverted(true);
    }

    public void sendTelemetry(){

        telemetryUtil.put("Turret Motor", turretMotor.get(), Tabs.ROBOT);

        telemetryUtil.put("Turret Encoder", turretEncoder.getPosition(), Tabs.ROBOT);

        telemetryUtil.put("Clockwise Limit Switch", cwBound.get(), Tabs.ROBOT);
        telemetryUtil.put("Counter Clockwise Limit Switch", ccwBound.get(), Tabs.ROBOT);

    }

}
