package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.behaviours.subsystem.LiftBehaviors;

public class liftTo extends Stage {

    double SPEED_CLAMP = 0.5;
    double stopRange;
    double targetPos;

    public liftTo(double pos, double srange) {
        targetPos = pos;
        stopRange = srange;
    }

    @Override public void init() {
        LiftBehaviors.liftPID.reset();
        LiftBehaviors.liftPID.target = targetPos;

    }
    

    @Override public boolean run(Robot r) {

        double PIDOut = -LiftBehaviors.liftPID.tick(r.telescope.liftVal(), Robot.dt, false);

        if (Math.abs(PIDOut) > SPEED_CLAMP) { PIDOut = SPEED_CLAMP * Math.signum(PIDOut); }

        r.telescope.liftMotor.set(PIDOut);

        return (Math.abs(r.telescope.retractVal() - targetPos) < stopRange);
    }


}
