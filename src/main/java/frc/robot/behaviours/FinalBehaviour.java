package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.functions.*;








public class FinalBehaviour {

    public static double startAngle = 0;

    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {

        double t = inputUtil.deadzoneAxis(r.input.joystick.getZ(), Constants.TURNING_DEADZONE);
        r.drive.pidController.target += t * 40 * Robot.dt;
        double PIDOut = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);


        V2d input = new V2d(
            inputUtil.deadzoneAxis(r.input.joystick.getX(), Constants.MOVEMENT_DEADZONE),
            inputUtil.deadzoneAxis(r.input.joystick.getY(), Constants.MOVEMENT_DEADZONE)
            );
        // rotate degrees goes clockwise, and angle retrieved goes cc
        // this means no inversion is necissary
        input = input.rotateDegrees(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - startAngle));


        driveUtil.setPowerMechanum(r.drive,
            input.x,
            input.y,
            PIDOut,
            r.input.joystick.getRawAxis(3));



        visionUtil.distanceFrom(r.vision.getArea());
        r.vision.pipelineTag(7);

    };

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        BehaviourUtil.stopDrive.accept(r);
        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();
        startAngle = r.drive.pidController.target;

    };
}

