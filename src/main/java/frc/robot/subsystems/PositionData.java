package frc.robot.subsystems;


import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;


public class PositionData {

    static Field2d f = new Field2d();

    public Translation2d center = new Translation2d(38.85/100, 30.25 / 100);
    public Translation2d m_backRightLocation = new Translation2d(13.0/100, 7.75/100);
    public Translation2d m_frontRightLocation = new Translation2d(64.7/100, 7.75/100);
    public Translation2d m_backLeftLocation = new Translation2d(13.0/100, 53.25/100);
    public Translation2d m_frontLeftLocation = new Translation2d(64.5/100, 53.25/100);

    //                      start loc
    public Pose2d pose = new Pose2d();

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
        m_frontLeftLocation.minus(center), m_frontRightLocation.minus(center),
        m_backLeftLocation.minus(center), m_backRightLocation.minus(center)
    );

    MecanumDriveOdometry m_odometry;





    public PositionData(GyroData gyro, DriveData drive) {
        m_odometry = new MecanumDriveOdometry(
            m_kinematics,
            gyro.globGyroscope.getRotation2d(),
            new MecanumDriveWheelPositions(
                drive.FLEncoder.getPosition(), drive.FREncoder.getPosition(),
                //drive.BLEncoder.getPosition(), drive.BREncoder.getPosition()
                1,1
            ),
            this.pose
            );
    }



    public void update(DriveData drive, GyroData gyro) {
        // Get my wheel positions
        var wheelPositions = new MecanumDriveWheelPositions(
                drive.FLEncoder.getPosition(), drive.FREncoder.getPosition(),
                //drive.BLEncoder.getPosition(), drive.BREncoder.getPosition()
                1,1
            );

        // Get the rotation of the robot from the gyro.
        var gyroAngle = gyro.globGyroscope.getRotation2d().unaryMinus();

        // Update the pose
        this.pose = m_odometry.update(gyroAngle, wheelPositions);

        f.setRobotPose(pose);
    }


    public void sendTelemetry() {

        telemetryUtil.put("Pos Estimate X", pose.getX(), Tabs.DEBUG);
        telemetryUtil.put("Pos Estimate Y", pose.getY(), Tabs.DEBUG);
        telemetryUtil.put("Pos Estimate T", pose.getRotation().getDegrees(), Tabs.DEBUG);
    }
}