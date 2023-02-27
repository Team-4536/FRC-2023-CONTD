package frc.robot.stages;

import frc.robot.Robot;


public abstract class Stage {

    public boolean requireForSet = true;
    public void setRequired(boolean x) { this.requireForSet = x; }

    public abstract boolean run(Robot r);
    public void init() { };
    public void end(Robot r) { };


}
