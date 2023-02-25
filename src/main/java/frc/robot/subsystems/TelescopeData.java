package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
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

    public TelescopeData(){

        liftEncoder.setInverted(false);
        retractEncoder.setInverted(true);
    }


    public void sendTelemetry(){

        telemetryUtil.put("Lift temp", liftMotor.getMotorTemperature(), Tabs.ROBOT);
        telemetryUtil.put("Retraction temp", retractMotor.getMotorTemperature(), Tabs.ROBOT);

        telemetryUtil.put("Lift Volts", liftMotor.getBusVoltage(), Tabs.ROBOT);
        telemetryUtil.put("Retraction Volts", retractMotor.getBusVoltage(), Tabs.ROBOT);

        telemetryUtil.put("Lift Power", liftMotor.get(), Tabs.ROBOT);
        telemetryUtil.put("Retract Power", retractMotor.get(), Tabs.ROBOT);

        telemetryUtil.put("Lift Encoder", liftVal(), Tabs.ROBOT);
        telemetryUtil.put("Retract encoder", retractVal(), Tabs.ROBOT);


        telemetryUtil.put("Upper Limit", upBound.get(), Tabs.ROBOT);
        telemetryUtil.put("Lower Limit", lowBound.get(), Tabs.ROBOT);

    }

    public double liftVal(){

        return liftEncoder.getPosition() - 100;

    }

    public double retractVal(){

        return retractEncoder.getPosition() - 100;

    }

    public void resetRetractEncoder(){
        retractEncoder.setPosition(100);
    }

    public void resetLiftEncoder(){
        liftEncoder.setPosition(100);
    }

 }

