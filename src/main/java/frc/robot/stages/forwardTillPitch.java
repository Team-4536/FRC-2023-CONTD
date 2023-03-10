package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import java.lang.Math;

public final class forwardTillPitch extends Stage {
    double wantedAngle;
    
    public forwardTillPitch(double wantedAngle) {
        this.wantedAngle = wantedAngle;
    }

    @Override public boolean run(Robot r) {
        driveUtil.setPowerMechPID(
            r,
            0,
            0.82,
            1);

        return (Math.abs(r.gyro.globGyroscope.getRoll()) > wantedAngle);
    }

}
