package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.hal.EncoderJNI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TelescopeData {


    public CANSparkMax liftMotor = new CANSparkMax(Hardware.ARM_LIFT_ID, Hardware.LIFT_MOTOR_TYPE);
    public CANSparkMax retractMotor = new CANSparkMax(Hardware.ARM_RETRACT_ID, Hardware.RETRACT_MOTOR_TYPE);

    public RelativeEncoder liftEncoder = liftMotor.getEncoder(com.revrobotics.SparkMaxRelativeEncoder.Type.kQuadrature, 8192);
    public RelativeEncoder retractEncoder = retractMotor.getEncoder(com.revrobotics.SparkMaxRelativeEncoder.Type.kQuadrature, 8192);


    public DigitalInput upBound = new DigitalInput(Hardware.UP_BOUND_ID);
    public DigitalInput lowBound = new DigitalInput(Hardware.LOW_BOUND_ID);




    public void sendTelemetry(){

        telemetryUtil.put("Lift Power", liftMotor.get(), Tabs.ROBOT);
        telemetryUtil.put("Retract Power", retractMotor.get(), Tabs.ROBOT);

        telemetryUtil.put("Lift Encoder", liftEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("Retract encoder", retractEncoder.getPosition(), Tabs.ROBOT);
        //telemetryUtil.put("Lift Position", liftEncoder.getPosition(), Tabs.ROBOT);
        //telemetryUtil.put("Retract Position", retractEncoder.getPosition(), Tabs.ROBOT);

        telemetryUtil.put("Upper Limit", upBound.get(), Tabs.ROBOT);
        telemetryUtil.put("Lower Limit", lowBound.get(), Tabs.ROBOT);

    }

}
