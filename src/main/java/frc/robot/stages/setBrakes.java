package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.constants.StageConstants;
import frc.robot.functions.pneumaticUtil;

public class setBrakes extends Stage{

    Timer flymer = new Timer();

    boolean foolean;

    public setBrakes(boolean active){

        foolean = active;

    }


    @Override public boolean run(Robot r) {

        if (foolean)
        {r.brakes.brakeSolenoid.set(Value.kForward);}
        else if (!foolean)
        {r.brakes.brakeSolenoid.set(Value.kReverse);}


        if (pneumaticUtil.getStatus(r.brakes.brakeSolenoid) == foolean)
        {
            flymer.start();
        }

        return flymer.get() > StageConstants.GRAB_WAIT;
    }
}
