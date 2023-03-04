package frc.robot.subsystems;


import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.robot.Robot;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;


public class PositionData {

    static Field2d f = new Field2d();

    //                      start loc
    public Pose2d pose = new Pose2d();

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
        new Translation2d(Hardware.FL_MOTOR_POS.x, Hardware.FL_MOTOR_POS.y),
        new Translation2d(Hardware.FR_MOTOR_POS.x, Hardware.FR_MOTOR_POS.y),
        new Translation2d(Hardware.BL_MOTOR_POS.x, Hardware.BL_MOTOR_POS.y),
        new Translation2d(Hardware.BR_MOTOR_POS.x, Hardware.BR_MOTOR_POS.y)
    );

    MecanumDrivePoseEstimator m_poseEstimate;





    public PositionData(GyroData gyro, DriveData drive) {
        
        this.pose = new Pose2d(new Translation2d(), new Rotation2d(0));


        m_poseEstimate = new MecanumDrivePoseEstimator(
            m_kinematics,
            gyro.globGyroscope.getRotation2d(),
            new MecanumDriveWheelPositions(
                drive.FLEncoder.getPosition(), drive.FREncoder.getPosition(),
                drive.BLEncoder.getPosition(), drive.BREncoder.getPosition()
            ),
            this.pose
            );
    }

    public void reInit() {
        Robot r = Robot.instance;

        this.pose = new Pose2d(new Translation2d(), new Rotation2d(0));

        m_poseEstimate = new MecanumDrivePoseEstimator(
            m_kinematics,
            r.gyro.globGyroscope.getRotation2d(),
            new MecanumDriveWheelPositions(
                r.drive.FLEncoder.getPosition(), r.drive.FREncoder.getPosition(),
                r.drive.BLEncoder.getPosition(), r.drive.BREncoder.getPosition()
            ),
            this.pose
            );
    }



    public void update() {
        Robot r = Robot.instance;


        // Get my wheel positions
        var wheelPositions = new MecanumDriveWheelPositions(
                r.drive.FLEncoder.getPosition(), r.drive.FREncoder.getPosition(),
                r.drive.BLEncoder.getPosition(), r.drive.BREncoder.getPosition()
            );

        // Get the rotation of the robot from the gyro.
        var gyroAngle = r.gyro.globGyroscope.getRotation2d().unaryMinus();

        // Update the pose
        this.pose = m_poseEstimate.update(gyroAngle, wheelPositions);
        double[] poseArr = Robot.instance.vision.pose.getDoubleArray(new double[] {});
        Pose2d camPose = new Pose2d(poseArr[0], poseArr[1], new Rotation2d(Math.toRadians(poseArr[5])));
        m_poseEstimate.addVisionMeasurement(camPose, Robot.timeSinceInit);

        f.setRobotPose(pose);
    }


    public void sendTelemetry() {

        telemetryUtil.put("Pos Estimate X", pose.getX(), Tabs.ROBOT);
        telemetryUtil.put("Pos Estimate Y", pose.getY(), Tabs.ROBOT);
        telemetryUtil.put("Pos Estimate T", pose.getRotation().getDegrees(), Tabs.ROBOT);
    }
}