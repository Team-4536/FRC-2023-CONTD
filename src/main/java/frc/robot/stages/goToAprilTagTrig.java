package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.constants.Hardware;
import frc.robot.constants.VisionConstants;
import frc.robot.controllers.PIDController;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.visionUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public final class goToAprilTagTrig extends Stage {

    int pip = 0;
    PIDController anglePID = new PIDController(0.04, 0.0004, -0.01);
    PIDController xPID = new PIDController(0.35, 0.005, -0.2);
    PIDController yPID = new PIDController(0.07, 0.006, -0.04);

    double wantedDistance = 24;

    public goToAprilTagTrig(int p, double dist) {

        this.pip = p;
        this.wantedDistance = dist;
    }




    @Override public boolean run(Robot r) {

        r.vision.pipelineTag(this.pip);

        V2d goal = new V2d(
            VisionConstants.CX_FROM_CENTER - (Hardware.ROBOT_X_SIZE_IN/2),
            wantedDistance + Hardware.ROBOT_Y_SIZE_IN - VisionConstants.CY_FROM_CENTER
            );

        xPID.target = goal.x;
        yPID.target = goal.y;




        if(r.vision.getTargets()) {

            V2d out = new V2d(
                // r.vision.getX(),
                //r.vision.getY()
                visionUtil.horizontalOffset(r.vision.getArea(), r.vision.getX()),
                visionUtil.distanceFrom(r.vision.getArea())
            );

            driveUtil.setPowerMechanum(r.drive,
            xPID.tick(out.x, Robot.dt, false),
            yPID.tick(out.y, Robot.dt, false),
            -anglePID.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true),
            0.4);

        }
        else {
            driveUtil.stop(r.drive);
            driveUtil.setPowerMechanum(r.drive,
            0,
            0,
            anglePID.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true),
            0.4);
        }








        boolean horizError = Math.abs(goal.x - visionUtil.horizontalOffset(r.vision.getArea(), r.vision.getX())) <= .65;
        boolean verticalError = Math.abs(goal.y - visionUtil.distanceFrom(r.vision.getArea())) <= 2.5;

        boolean motorSpeed = yPID.prevErr - (goal.y - visionUtil.distanceFrom(r.vision.getArea())) <= .15;

        telemetryUtil.put("Horizontal Err", horizError, Tabs.DEBUG);
        telemetryUtil.put("Vertical Err", verticalError, Tabs.DEBUG);
        telemetryUtil.put("Motor Speed", motorSpeed, Tabs.DEBUG);

        return (motorSpeed && horizError && verticalError);
    }

}
