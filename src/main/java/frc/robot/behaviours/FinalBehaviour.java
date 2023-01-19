package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.visionUtil;


public class FinalBehaviour {

    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {

        r.drive.pidController.target += r.input.joystick.getZ() * 40 * Robot.dt;
        //driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
        driveUtil.setPowerMechanum(r.drive, r.input.joystick.getX(), r.input.joystick.getY(), r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true), r.input.joystick.getRawAxis(3));
        visionUtil.distanceFrom(r.vision.getArea());
        r.vision.pipeline7();

    };

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();

    };
}

