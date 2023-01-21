
package frc.robot.stages;

import java.util.function.Function;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.controllers.PIDController;
import frc.robot.functions.driveUtil;
import frc.robot.functions.gyroUtil;
import frc.robot.functions.visionUtil;

public class AutoStages {

    public static boolean GoToAngle(PIDController pid, double angle, double range, Robot r) {

        pid.target = angle;
        double out = -pid.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerMechanum(r.drive, 0, 0, out, 1);
        return Math.abs(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - angle)) < (range/2);
    }



    public static class goTo90 extends Stage {
        // 90
        // public static PIDController pidController = new PIDController(0.007, 0.002, -0.05);
        // 180
        public PIDController pidController = new PIDController(0.004, 0.002, -0.13);

        @Override public boolean run(Robot r) {
            return GoToAngle(pidController, 180, 1, r);
        }

    }
    

    public static class goToAprilTag extends Stage {

        int pip = 0;
        PIDController anglePID = new PIDController(0.05, 0.0, -0.01);
        PIDController xPID = new PIDController(0.05, 0.0, -0.01);
        PIDController yPID = new PIDController(0.05, 0.0, -0.01);

        public goToAprilTag(int p) { this.pip = p; }


        @Override public boolean run(Robot r) {
            r.vision.pipelineTag(this.pip);
            V2d goal = new V2d(0, 9);
            xPID.target = goal.x;
            yPID.target = goal.y;
            
            if(r.vision.getTargets()) {

                V2d out = new V2d(
                    r.vision.getX(),
                    r.vision.getY()
                );

                driveUtil.setPowerMechanum(r.drive, 
                -xPID.tick(out.x, Robot.dt, false),
                -yPID.tick(out.y, Robot.dt, false),
                -anglePID.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true),
                0.4);
            }
            else {
                driveUtil.stop(r.drive);
            }

            return false;
        }

    }
}