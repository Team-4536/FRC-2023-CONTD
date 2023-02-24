package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.functions.robotUtil;

public final class timedPause extends Stage {

    Timer flymer = new Timer();

    double rtime;

    public timedPause(double time){

        rtime = time;

    }


    @Override public void init(){
        flymer.reset();
        flymer.start(); }


    @Override public boolean run(Robot r) {
        robotUtil.stopRobot(r);
        return flymer.get() > rtime;
    }

}
