package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TelescopeData {


    public CANSparkMax liftMotor = new CANSparkMax(Hardware.ARM_LIFT_ID, Hardware.LIFT_MOTOR_TYPE);
    public CANSparkMax retractMotor = new CANSparkMax(Hardware.ARM_RETRACT_ID, Hardware.RETRACT_MOTOR_TYPE);

    public Encoder liftEncoder = new Encoder(2, 3);
    public Encoder retractEncoder = new Encoder(0, 1);

    public DigitalInput upBound = new DigitalInput(Hardware.UP_BOUND_ID);
    public DigitalInput lowBound = new DigitalInput(Hardware.LOW_BOUND_ID);




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
