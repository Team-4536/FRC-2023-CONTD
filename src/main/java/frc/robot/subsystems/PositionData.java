package frc.robot.subsystems;


import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;


public class PositionData {


    public Translation2d m_backRightLocation = new Translation2d(13.0/100, 7.75/100);
    public Translation2d m_frontRightLocation = new Translation2d(64.7/100, 7.75/100);
    public Translation2d m_backLeftLocation = new Translation2d(13.0/100, 53.25/100);
    public Translation2d m_frontLeftLocation = new Translation2d(64.5/100, 53.25/100);

    //                      start loc
    public Pose2d pose = new Pose2d();

    // Creating my kinematics object using the wheel locations.
    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
        m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
    );

    // Creating my odometry object from the kinematics object and the initial wheel positions.
    // Here, our starting pose is 5 meters along the long end of the field and in the
    // center of the field along the short end, facing the opposing alliance wall.
    MecanumDriveOdometry m_odometry;

    public PositionData(GyroData gyro, DriveData drive) {
        m_odometry = new MecanumDriveOdometry(
            m_kinematics,
            gyro.globGyroscope.getRotation2d(),
            new MecanumDriveWheelPositions(
                drive.FLEncoder.getDistance(), drive.FREncoder.getDistance(),
                drive.BREncoder.getDistance(), drive.BREncoder.getDistance()
            ),
            this.pose
            );

    }



    public void update(DriveData drive, GyroData gyro) {
        // Get my wheel positions
        var wheelPositions = new MecanumDriveWheelPositions(
                drive.FLEncoder.getDistance(), drive.FREncoder.getDistance(),
                drive.BREncoder.getDistance(), drive.BREncoder.getDistance()
            );

        // Get the rotation of the robot from the gyro.
        var gyroAngle = gyro.globGyroscope.getRotation2d();

        // Update the pose
        this.pose = m_odometry.update(gyroAngle, wheelPositions);
    }
}