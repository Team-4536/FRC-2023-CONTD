package frc.robot.Simulation;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.GyroData;
import frc.robot.subsystems.PositionData;

public class SimulationDrive extends DriveData{
    
    GyroData gyro = new GyroData();
    DriveData drive = new DriveData();

    //our gyroscope and drivedata

    public DifferentialDrivetrainSim driveSim = new DifferentialDrivetrainSim(DCMotor.getNEO(4), 7.29, 7.5, 68.0, 
    Units.inchesToMeters(4.5), Units.inchesToMeters(23.667), 
    VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

    //we don't have a differentisal drivetrain so I made one, for sim purposes should be just fine

    private EncoderSim FLEncoderSim = new EncoderSim(FLEncoder);
    private EncoderSim FREncoderSim = new EncoderSim(FREncoder);
    private EncoderSim BLEncoderSim = new EncoderSim(BLEncoder);
    private EncoderSim BREncoderSim = new EncoderSim(BREncoder);

    //we have four motors, with four encoders, but the sim coed is stupid and only wants 2 motors?? 
    //i'll try to distribute speed and voltage properly to all mecanums with just 2 sim motors, should work like a dream

    private Field2d field = new Field2d();

    //simulation field

    private PositionData pData = new PositionData(gyro, drive);

    //real position data

    public void SimulationPeriodic(AnalogGyroSim gyroSim){

        driveSim.setInputs((frontLeftDrive.get() + backLeftDrive.get()) 
        * RobotController.getInputVoltage(), 
        (frontRightDrive.get() + backRightDrive.get()) 
        * RobotController.getInputVoltage());

        driveSim.update(0.02);

        FLEncoderSim.setDistance(driveSim.getLeftPositionMeters());
        FLEncoderSim.setRate(driveSim.getLeftVelocityMetersPerSecond());
        FREncoderSim.setDistance(driveSim.getRightPositionMeters());
        FREncoderSim.setRate(driveSim.getRightVelocityMetersPerSecond());
        gyroSim.setAngle(-driveSim.getHeading().getDegrees());

    }

    //distributes voltage from all four motors to just two motors and sets some parameters
    
    public void sendTelemetry(){SmartDashboard.putData("Field", field);}

    public void Periodic(){

        pData.update(drive, gyro);
        field.setRobotPose(pData.m_odometry.getPoseMeters());

    }

}
