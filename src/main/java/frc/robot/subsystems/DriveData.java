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

        BREncoder.setPositionConversionFactor(Hardware.ENCODER_CONVERSION);
        FLEncoder.setPositionConversionFactor(Hardware.ENCODER_CONVERSION);
        BLEncoder.setPositionConversionFactor(Hardware.ENCODER_CONVERSION);
        FREncoder.setPositionConversionFactor(Hardware.ENCODER_CONVERSION);
    }


    public void sendTelemetry() {

        telemetryUtil.put("FL Pwr", FLDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("FR Pwr", FRDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BL Pwr", BLDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BR Pwr", BRDrive.get(), Tabs.ROBOT);

        telemetryUtil.put("BL Distance", BLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("BR Distance", BREncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FL Distance", FLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FR Distance", FREncoder.getPosition(), Tabs.ROBOT);

        telemetryUtil.put("FL Volts", FLDrive.getBusVoltage(), Tabs.ROBOT);
        telemetryUtil.put("FR Volts", FRDrive.getBusVoltage(), Tabs.ROBOT);
        telemetryUtil.put("BL Volts", BLDrive.getBusVoltage(), Tabs.ROBOT);
        telemetryUtil.put("BR Volts", BRDrive.getBusVoltage(), Tabs.ROBOT);
    }
}
