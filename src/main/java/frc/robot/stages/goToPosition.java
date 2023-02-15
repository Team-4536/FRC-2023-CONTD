package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class goToPosition extends Stage {

    public V2d targetPos = new V2d();

    public goToPosition(V2d t, Robot r) {
        this.targetPos = t; 
        r.drive.pidController.reset();
    }

    @Override
    public boolean run(Robot r) {


        V2d error = new V2d(r.positionData.pose).sub(targetPos);
        telemetryUtil.put("Position Error X", error.x, Tabs.DEBUG);
        telemetryUtil.put("Position Error Y", error.y, Tabs.DEBUG);

        double a = -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerMechanum(r.drive, -error.x, -error.y, a, 0.05);

        return false;


    }
}
