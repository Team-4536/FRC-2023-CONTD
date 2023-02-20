package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public final class Flymer extends Stage {

    Timer flymer = new Timer();



    @Override public void init(){
        flymer.start(); }


    @Override public boolean run(Robot r) {
        return flymer.get() > 0.5f; }

}
