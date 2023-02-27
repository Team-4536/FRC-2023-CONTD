package frc.robot.stages;

import frc.robot.Robot;


public abstract class Stage {

    public boolean requireForSet = true;

    public abstract boolean run(Robot r);
    public void init() { };
    public void end(Robot r) { };


}
