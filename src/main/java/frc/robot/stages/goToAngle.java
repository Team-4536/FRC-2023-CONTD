package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.gyroUtil;

public final class goToAngle extends Stage {


    double targetAngle = 0;
    double stopRange = 4;



    public goToAngle(double target) {
        this.targetAngle = target;
    }


    @Override public boolean run(Robot r) {

        driveUtil.pid.target = this.targetAngle;
        driveUtil.setPowerMechPID(r, 0, 0, 0.8);

        return Math.abs(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - this.targetAngle)) < (this.stopRange/2);
    }

    @Override public void end(Robot r) {
        driveUtil.stop(r.drive);
    }
}

