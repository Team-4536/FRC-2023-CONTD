package frc.robot.subsystems;

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

public class SimulationDrive extends DriveData{
    
    GyroData gyro = new GyroData();
    DriveData drive = new DriveData();

    public DifferentialDrivetrainSim driveSim = new DifferentialDrivetrainSim(DCMotor.getNEO(4), 7.29, 7.5, 68.0, 
    Units.inchesToMeters(4.5), Units.inchesToMeters(23.667), 
    VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

    private EncoderSim FLEncoderSim = new EncoderSim(FLEncoder);
    private EncoderSim FREncoderSim = new EncoderSim(FREncoder);
    private EncoderSim BLEncoderSim = new EncoderSim(BLEncoder);
    private EncoderSim BREncoderSim = new EncoderSim(BREncoder);

    private Field2d field = new Field2d();

    private PositionData pData = new PositionData(gyro, drive);

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
    
    public void sendTelemetry(){SmartDashboard.putData("Field", field);}

    public void Periodic(){

        pData.update(drive, gyro);
        field.setRobotPose(pData.m_odometry.getPoseMeters());

    }

}
