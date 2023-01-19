package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.PositionData;

public class TestingBehaviour {


    static PositionData p = new PositionData();
    static Field2d f = new Field2d();

    public static Consumer<Robot> encoderPeriodic = r -> {

        // double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis()*2 - 1);
        p.update(Robot.dt, r.drive, r.gyro);
        p.sendTelemetry();


        SmartDashboard.putData(f);
        f.setRobotPose(p.prevPosition.x, p.prevPosition.y, new Rotation2d(r.gyro.globGyroscope.getAngle()));
    };






    public static Consumer<Robot> encoderInit = r -> {
        p.prevVelocity = new V2d(0, -9);
    };




    public static Consumer<Robot> testPeriodic = r -> {
        telemetryUtil.put("Testing str", "HI THERE", Tabs.DEBUG);
    };


}
