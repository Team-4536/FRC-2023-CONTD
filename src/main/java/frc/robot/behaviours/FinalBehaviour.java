package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.visionUtil;
import frc.robot.subsystems.PositionData;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.robot.Constants;
import frc.robot.V2d;
import frc.robot.functions.*;



public class FinalBehaviour {

    static PositionData p = null;
    static Field2d f = new Field2d();
    static double startAngle = 0;


    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {


        // angular
        double t = inputUtil.deadzoneAxis(r.input.controller.getRightX(), Constants.TURNING_DEADZONE);
        r.drive.pidController.target += t * 90 * Robot.dt;

        double PIDOut = -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);


        // linear
        V2d input = new V2d(
            inputUtil.deadzoneAxis(r.input.controller.getLeftX(), Constants.MOVEMENT_DEADZONE),
            inputUtil.deadzoneAxis(r.input.controller.getLeftY(), Constants.MOVEMENT_DEADZONE)
            );

        input = input.rotateDegrees(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - startAngle));




        driveUtil.setPowerMechanum(r.drive,
            input.x,
            input.y,
            PIDOut,
            r.input.joystick.getRawAxis(3));

        // end of drive stuff :)






        visionUtil.distanceFrom(r.vision.getArea());
        //r.vision.pipelineTag(7);


        if(p != null) {

            p.update(r.drive, r.gyro);
            SmartDashboard.putData("Field", f);
        }
    };

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        BehaviourUtil.stopDrive.accept(r);
        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();
        startAngle = r.drive.pidController.target;



        p = new PositionData(r.gyro, r.drive);
        f.setRobotPose(p.pose);
    };
}

