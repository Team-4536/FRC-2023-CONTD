package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class goToPosition extends Stage {

    public V2d targetPos = new V2d();

    public goToPosition(V2d t) {
        this.targetPos = t;
    }

    @Override public void init() {
        driveUtil.pid.reset();
        driveUtil.pid.target = Robot.instance.gyro.getYaw();
    }

    @Override
    public boolean run(Robot r) {


        V2d error = new V2d(r.positionData.pose).sub(targetPos);
        double temp = error.x;
        error.x = error.y;
        error.y = temp;

        telemetryUtil.put("Position Error X", error.x, Tabs.DEBUG);
        telemetryUtil.put("Position Error Y", error.y, Tabs.DEBUG);

        if(error.x > 1.0f) { error.x = 1.0f; }
        if(error.x < -1.0f) { error.x = -1.0f; }

        if(error.y > 1.0f) { error.y = 1.0f; }
        if(error.y < -1.0f) { error.y = -1.0f; }

        driveUtil.setPowerMechPID(r, error.x, -error.y, 0.01);

        return false;


    }
}
