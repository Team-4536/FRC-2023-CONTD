package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.controllers.PIDController;

public class DriveData {

    public PIDController pidController = new PIDController(0.05, 0.0, -0.01);

    public WPI_VictorSPX frontLeftDrive;
    public WPI_VictorSPX frontRightDrive;
    public WPI_VictorSPX backLeftDrive;
    public WPI_VictorSPX backRightDrive;

    public DriveData() {
        
        this.frontLeftDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_LEFT_PORT);
        this.frontRightDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_RIGHT_PORT);
        this.backLeftDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_LEFT_PORT);
        this.backRightDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_RIGHT_PORT);

        this.frontLeftDrive.setInverted(Constants.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Constants.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Constants.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Constants.DRIVE_BACK_RIGHT_FLIPPED);


    }

    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());
    }
}
