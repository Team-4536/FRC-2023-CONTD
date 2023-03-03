package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.utils.V2d;

public final class moveTimed extends Stage {

    Timer flymer = new Timer();

    double scale;
    double rTime;

    V2d direction;


    public moveTimed(double mscale, V2d vector, double time){

        scale = mscale;
        direction = vector;
        rTime = time;

    }


    @Override public void init(){
        flymer.reset();
        flymer.start(); }


    @Override public boolean run(Robot r) {
        driveUtil.setPowerMechPID(r, direction.x, direction.y, scale);
        return flymer.get() > rTime;
    }

}
