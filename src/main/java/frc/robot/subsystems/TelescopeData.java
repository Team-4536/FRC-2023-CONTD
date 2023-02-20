package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.Constants.MechanismInfo;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TelescopeData {

    public CANSparkMax liftMotor = new CANSparkMax(MechanismInfo.ARM_LIFT_ID, MechanismInfo.ARM_MOTOR_TYPE);
    public CANSparkMax retractMotor = new CANSparkMax(MechanismInfo.ARM_RETRACT_ID, MechanismInfo.ARM_MOTOR_TYPE);

    public Encoder liftEncoder = new Encoder(2, 3);
    public Encoder retractEncoder = new Encoder(0, 1);

    public DigitalInput upBound = new DigitalInput(Constants.UP_BOUND_ID);
    public DigitalInput lowBound = new DigitalInput(Constants.LOW_BOUND_ID);

    public void sendTelemetry(){

        telemetryUtil.put("Lift Power", liftMotor.get(), Tabs.ROBOT);
        telemetryUtil.put("Retract Power", retractMotor.get(), Tabs.ROBOT);

        telemetryUtil.put("Lift Encoder", liftEncoder.get(), Tabs.ROBOT);
        telemetryUtil.put("Retract encoder", retractEncoder.get(), Tabs.ROBOT);
        //telemetryUtil.put("Lift Position", liftEncoder.getPosition(), Tabs.ROBOT);
        //telemetryUtil.put("Retract Position", retractEncoder.getPosition(), Tabs.ROBOT);

        telemetryUtil.put("Upper Limit", upBound.get(), Tabs.ROBOT);
        telemetryUtil.put("Lower Limit", lowBound.get(), Tabs.ROBOT);

    }

}
