package frc.robot.subsystems;


import com.revrobotics.*;

import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.constants.Hardware;

public class DriveData {

    public CANSparkMax FLDrive;
    public CANSparkMax FRDrive;
    public CANSparkMax BLDrive;
    public CANSparkMax BRDrive;

    public RelativeEncoder FLEncoder;
    public RelativeEncoder FREncoder;
    public RelativeEncoder BLEncoder;
    public RelativeEncoder BREncoder;

    public DriveData() {

        this.FLDrive = new CANSparkMax(Hardware.DRIVE_FRONT_LEFT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.FRDrive = new CANSparkMax(Hardware.DRIVE_FRONT_RIGHT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.BLDrive = new CANSparkMax(Hardware.DRIVE_BACK_LEFT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.BRDrive = new CANSparkMax(Hardware.DRIVE_BACK_RIGHT_PORT, Hardware.DRIVE_MOTOR_TYPE);

        this.FLDrive.setInverted(Hardware.DRIVE_FRONT_LEFT_FLIPPED);
        this.FRDrive.setInverted(Hardware.DRIVE_FRONT_RIGHT_FLIPPED);
        this.BLDrive.setInverted(Hardware.DRIVE_BACK_LEFT_FLIPPED);
        this.BRDrive.setInverted(Hardware.DRIVE_BACK_RIGHT_FLIPPED);

        // ChannelA is the one with 3 wires channelB is the one with 1 wire(TRUE)
        FLEncoder = FLDrive.getEncoder();
        FREncoder = FRDrive.getEncoder();
        BLEncoder = BLDrive.getEncoder();
        BREncoder = BRDrive.getEncoder();

        BREncoder.setPositionConversionFactor(1);
        FLEncoder.setPositionConversionFactor(1);
        BLEncoder.setPositionConversionFactor(1);
        FREncoder.setPositionConversionFactor(1);
    }


    public void sendTelemetry() {

        telemetryUtil.put("FL Pwr", FLDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("FR Pwr", FRDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BL Pwr", BLDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BR Pwr", BRDrive.get(), Tabs.ROBOT);

        telemetryUtil.put("BL Encoder", BLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("BR Encoder", BREncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FL Encoder", FLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FR Encoder", FREncoder.getPosition(), Tabs.ROBOT);

        telemetryUtil.put("FL temp", FLDrive.getMotorTemperature(), Tabs.ROBOT);
        telemetryUtil.put("FR temp", FRDrive.getMotorTemperature(), Tabs.ROBOT);
        telemetryUtil.put("BL temp", BLDrive.getMotorTemperature(), Tabs.ROBOT);
        telemetryUtil.put("BR temp", BRDrive.getMotorTemperature(), Tabs.ROBOT);

        telemetryUtil.put("FL Volts", FLDrive.getBusVoltage() > 5.5, Tabs.ROBOT);
        telemetryUtil.put("FR Volts", FRDrive.getBusVoltage() > 5.5, Tabs.ROBOT);
        telemetryUtil.put("BL Volts", BLDrive.getBusVoltage() > 5.5, Tabs.ROBOT);
        telemetryUtil.put("BR Volts", BRDrive.getBusVoltage() > 5.5, Tabs.ROBOT);
    }
}
