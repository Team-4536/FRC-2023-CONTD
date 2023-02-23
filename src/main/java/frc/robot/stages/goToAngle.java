package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.gyroUtil;

public final class goToAngle extends Stage {


    // how to tune w/ diff jumps?

    // 90
    // public static PIDController pidController = new PIDController(0.007, 0.002, -0.05);
    // 180
    double targetAngle = 180;
    double stopRange = 1;



    public goToAngle(double target, double srange) {
        this.targetAngle = 180;
        this.stopRange = srange;
    }


    @Override public boolean run(Robot r) {

        driveUtil.pid.target = this.targetAngle;
        driveUtil.setPowerMechPID(r, 0, 0, 1);

        return Math.abs(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - this.targetAngle)) < (this.stopRange/2);
    }
}

