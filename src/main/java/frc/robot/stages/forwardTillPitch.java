package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import java.lang.Math;

import edu.wpi.first.wpilibj.Timer;

public final class forwardTillPitch extends Stage {
    double wantedAngle;
    Timer flymer;
    
    public forwardTillPitch(double wantedAngle) {
        this.wantedAngle = wantedAngle;
        flymer = new Timer();
    }

    @Override public boolean run(Robot r) {

        driveUtil.setPowerMechPID(
            r,
            0,
            1.0,
            1);

            if (Math.abs(r.gyro.globGyroscope.getRoll()) > wantedAngle) { flymer.start();}

        return (flymer.get() >= 0.12 );
    }

}
