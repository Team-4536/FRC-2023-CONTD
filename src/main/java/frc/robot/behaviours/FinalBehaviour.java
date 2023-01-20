package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.visionUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.PositionData;


public class FinalBehaviour {

    static PositionData p = new PositionData();

    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {

        r.drive.pidController.target += r.input.controller.getRightX() * 40 * Robot.dt;
        //driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
        driveUtil.setPowerMechanum(r.drive, r.input.controller.getLeftX(), r.input.controller.getLeftY(), -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true), r.input.joystick.getRawAxis(3));
        visionUtil.distanceFrom(r.vision.getArea());
        r.vision.pipeline7();


        // double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        //driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis()*2 - 1);
        p.updateBasic(Robot.dt, r.drive, r.gyro);
        p.sendTelemetry();
        
        telemetryUtil.put("pos", p.prevPosition.toString(), Tabs.DEBUG);

    };

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();

    };
}

