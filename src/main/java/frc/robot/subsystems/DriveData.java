package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.controllers.PIDController;

public class DriveData {

    public PIDController pidController = new PIDController(0.08, 0.01, 0);

    public WPI_VictorSPX frontLeftDrive;
    public WPI_VictorSPX frontRightDrive;
    public WPI_VictorSPX backLeftDrive;
    public WPI_VictorSPX backRightDrive;

    public Encoder FLEncoder;
    public Encoder FREncoder;
    public Encoder BLEncoder;
    public Encoder BREncoder;

    public DriveData() {
        
        this.frontLeftDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_LEFT_PORT);
        this.frontRightDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_RIGHT_PORT);
        this.backLeftDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_LEFT_PORT);
        this.backRightDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_RIGHT_PORT);

        this.frontLeftDrive.setInverted(Constants.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Constants.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Constants.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Constants.DRIVE_BACK_RIGHT_FLIPPED);

        //ChannelA is the one with 3 wires channelB is the one with 1 wire(TRUE)
        FLEncoder = new Encoder(6, 7);
        FREncoder = new Encoder(4, 5);
        BLEncoder = new Encoder(0, 1);
        BREncoder = new Encoder(2, 3);

        BREncoder.setReverseDirection(false);
        FLEncoder.setReverseDirection(true);
        BLEncoder.setReverseDirection(true);
        FREncoder.setReverseDirection(false);

    }
    


    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());

        SmartDashboard.putNumber("BLeft Encoder", BLEncoder.get());
        SmartDashboard.putNumber("BRight Encoder", BREncoder.get());
        SmartDashboard.putNumber("FLeft Encoder", FLEncoder.get());
        SmartDashboard.putNumber("FRight Encoder", FREncoder.get());
    }
}
