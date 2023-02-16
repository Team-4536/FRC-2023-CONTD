package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.controllers.PIDController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

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

        BREncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        BLEncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        FREncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
        FLEncoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);

    }
    


    public void sendTelemetry() {

        telemetryUtil.put("FL Pwr", frontLeftDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("FR Pwr", frontRightDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BL Pwr", backLeftDrive.get(), Tabs.ROBOT);
        telemetryUtil.put("BR Pwr", backRightDrive.get(), Tabs.ROBOT);

        telemetryUtil.put("BLeft Encoder", BLEncoder.get(), Tabs.ROBOT);
        telemetryUtil.put("BRight Encoder", BREncoder.get(), Tabs.ROBOT);
        telemetryUtil.put("FLeft Encoder", FLEncoder.get(), Tabs.ROBOT);
        telemetryUtil.put("FRight Encoder", FREncoder.get(), Tabs.ROBOT);

    }
}
