package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.visionUtil;
import frc.robot.subsystems.PositionData;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.FieldObject2d;


public class FinalBehaviour {

    static PositionData p = null;
    static Field2d f = new Field2d();

    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {

        r.drive.pidController.target += r.input.controller.getRightX() * 90 * Robot.dt;
        //driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
        driveUtil.setPowerMechanum(r.drive, r.input.controller.getLeftX(), r.input.controller.getLeftY(), -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true), r.input.joystick.getRawAxis(3));
        visionUtil.distanceFrom(r.vision.getArea());
        r.vision.pipeline7();


        if(p != null) {

            p.update(r.drive, r.gyro);
            SmartDashboard.putData("Field", f);
        }
    };

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();
        p = new PositionData(r.gyro, r.drive);
        f.setRobotPose(p.pose);

    };
}

