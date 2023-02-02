package frc.robot.subsystems;


import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.controllers.PIDController;

public class DriveData {

    public PIDController pidController = new PIDController(0.08, 0.01, 0);

    public CANSparkMax frontLeftDrive;
    public CANSparkMax frontRightDrive;
    public CANSparkMax backLeftDrive;
    public CANSparkMax backRightDrive;

    public RelativeEncoder FLEncoder;
    public RelativeEncoder FREncoder;
    public RelativeEncoder BLEncoder;
    public RelativeEncoder BREncoder;

    public SparkMaxAbsoluteEncoder cum;

    public DriveData() {
        
        this.frontLeftDrive = new CANSparkMax(Constants.DRIVE_FRONT_LEFT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.frontRightDrive = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.backLeftDrive = new CANSparkMax(Constants.DRIVE_BACK_LEFT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.backRightDrive = new CANSparkMax(Constants.DRIVE_BACK_RIGHT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

        this.frontLeftDrive.setInverted(Constants.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Constants.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Constants.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Constants.DRIVE_BACK_RIGHT_FLIPPED);

        //ChannelA is the one with 3 wires channelB is the one with 1 wire(TRUE)
        FLEncoder = frontLeftDrive.getEncoder();
        FREncoder = frontRightDrive.getEncoder();
        BLEncoder = backLeftDrive.getEncoder();
        BREncoder = backRightDrive.getEncoder();

        BREncoder.setInverted(false);
        FLEncoder.setInverted(true);
        BLEncoder.setInverted(true);
        FREncoder.setInverted(false);

        BREncoder.setPositionConversionFactor(1);
        FLEncoder.setPositionConversionFactor(1);
        BLEncoder.setPositionConversionFactor(1);
        FREncoder.setPositionConversionFactor(1);

        //BREncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        //BLEncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        //FREncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        //FLEncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);

    }
    


    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());

        SmartDashboard.putNumber("BLeft Encoder", BLEncoder.getPosition());
        SmartDashboard.putNumber("BRight Encoder", BREncoder.getPosition());
        SmartDashboard.putNumber("FLeft Encoder", FLEncoder.getPosition());
        SmartDashboard.putNumber("FRight Encoder", FREncoder.getPosition());
    }
}
