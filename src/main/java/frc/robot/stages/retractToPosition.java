package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.behaviours.subsystem.RetractionBehaviors;
import frc.robot.constants.StageConstants;
import frc.robot.functions.telescopeUtil;
import frc.robot.utils.mathUtil;

public class retractToPosition extends Stage {

    double goalPosition;
    double pError;

    public retractToPosition(double position, double error){

        goalPosition = position;
        pError = error;

    }

    @Override public void init() {
        RetractionBehaviors.retractPID.reset();
        RetractionBehaviors.retractPID.target = goalPosition;

    }

    @Override public boolean run(Robot r) {


        double PIDOut = -RetractionBehaviors.retractPID.tick(r.telescope.retractVal(), Robot.dt, false);
        PIDOut = mathUtil.clampLen(PIDOut, StageConstants.RETRACT_SPEED_CLAMP);

        telescopeUtil.softLimitRetract(r.telescope, PIDOut);

        return (Math.abs(r.telescope.retractVal() - goalPosition) < pError);

    }


    @Override
    public void end(Robot r) {
        r.telescope.retractMotor.set(0);
    }
}
