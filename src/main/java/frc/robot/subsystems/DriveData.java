package frc.robot.subsystems;


import com.revrobotics.*;

import frc.robot.controllers.PIDController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.constants.Hardware;

public class DriveData {

    public static boolean joystickDrive = false;

    public PIDController pidController = new PIDController(0.01, 0.001, -0.001);

    public CANSparkMax frontLeftDrive;
    public CANSparkMax frontRightDrive;
    public CANSparkMax backLeftDrive;
    public CANSparkMax backRightDrive;

    public RelativeEncoder FLEncoder;
    public RelativeEncoder FREncoder;
    public RelativeEncoder BLEncoder;
    public RelativeEncoder BREncoder;

    public DriveData() {

        this.frontLeftDrive = new CANSparkMax(Hardware.DRIVE_FRONT_LEFT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.frontRightDrive = new CANSparkMax(Hardware.DRIVE_FRONT_RIGHT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.backLeftDrive = new CANSparkMax(Hardware.DRIVE_BACK_LEFT_PORT, Hardware.DRIVE_MOTOR_TYPE);
        this.backRightDrive = new CANSparkMax(Hardware.DRIVE_BACK_RIGHT_PORT, Hardware.DRIVE_MOTOR_TYPE);

        this.frontLeftDrive.setInverted(Hardware.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Hardware.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Hardware.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Hardware.DRIVE_BACK_RIGHT_FLIPPED);

        // ChannelA is the one with 3 wires channelB is the one with 1 wire(TRUE)
        FLEncoder = frontLeftDrive.getEncoder();
        FREncoder = frontRightDrive.getEncoder();
        BLEncoder = backLeftDrive.getEncoder();
        BREncoder = backRightDrive.getEncoder();

        BREncoder.setPositionConversionFactor(1);
        FLEncoder.setPositionConversionFactor(1);
        BLEncoder.setPositionConversionFactor(1);
        FREncoder.setPositionConversionFactor(1);

        //BREncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
    }


    public void sendTelemetry() {

        telemetryUtil.put("FL Pwr", frontLeftDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("FR Pwr", frontRightDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BL Pwr", backLeftDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BR Pwr", backRightDrive.get(), Tabs.ROBOT);

        telemetryUtil.put("BLeft Encoder", BLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("BRight Encoder", BREncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FLeft Encoder", FLEncoder.getPosition(), Tabs.ROBOT);
        telemetryUtil.put("FRight Encoder", FREncoder.getPosition(), Tabs.ROBOT);

    }
}
