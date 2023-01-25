package frc.robot.behaviours;

import java.util.function.Consumer;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.visionUtil;
import frc.robot.subsystems.PositionData;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import frc.robot.V2d;
import frc.robot.Constants.ControlInfo;
import frc.robot.functions.*;



public class FinalBehaviour {

    static PositionData p = null;
    static Field2d f = new Field2d();
    static double startAngle = 0;
    static boolean buttonIsPressed = false;
    static int runNumber = 0;


    public static @Hidden Consumer<Robot> teleOpPeriodic = r -> {

        if(r.input.controller.getBButtonPressed()){
            
            runNumber++;

            if(runNumber == 1){
                resetAuto(r);
                stages.add(new AutoStages.goToAprilTag(1));

            } else{
                BehaviourUtil.stopDrive.accept(r);
            }

        } else {
        
            runNumber = 0;
            // angular
            double t = inputUtil.deadzoneAxis(r.input.controller.getRightX(), ControlInfo.TURNING_DEADZONE);

            double turningScalar = inputUtil.mapInput(
                1-r.input.controller.getLeftTriggerAxis(),
                1, 0, ControlInfo.MAX_TURNING_OUT, ControlInfo.DEFAULT_TURNING_OUT);
            r.drive.pidController.target += t * turningScalar * Robot.dt;

            double PIDOut = -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);



            // linear
            double driveScalar = inputUtil.mapInput(
                1-r.input.controller.getRightTriggerAxis(),
                1, 0, ControlInfo.MAX_DRIVE_OUT, ControlInfo.DEFAULT_DRIVE_OUT);

            double xIn = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), ControlInfo.MOVEMENT_DEADZONE);
            xIn *= driveScalar;
            double yIn = inputUtil.deadzoneAxis(r.input.controller.getLeftY(), ControlInfo.MOVEMENT_DEADZONE);
            yIn *= driveScalar;


            V2d input = new V2d( xIn, yIn);
            input = input.rotateDegrees(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - startAngle));




            driveUtil.setPowerMechanum(r.drive,
                input.x,
                input.y,
                PIDOut,
                r.input.joystick.getRawAxis(3));

            // end of drive stuff :)
        }






        visionUtil.distanceFrom(r.vision.getArea());
        r.vision.pipelineTag(7);


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

