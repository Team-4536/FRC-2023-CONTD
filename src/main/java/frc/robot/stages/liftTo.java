package frc.robot.stages;

import frc.robot.Robot;
import frc.robot.behaviours.subsystem.LiftBehaviors;
import frc.robot.constants.ControlSettings;
import frc.robot.utils.mathUtil;

public class liftTo extends Stage {

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
        PIDOut = mathUtil.clamp(PIDOut, ControlSettings.LIFT_MOTOR_MAX_OUTPUT, -ControlSettings.LIFT_MOTOR_MAX_OUTPUT);

        r.telescope.liftMotor.set(PIDOut);

        return (Math.abs(r.telescope.liftVal() - targetPos) < stopRange);
    }

    @Override
    public void end(Robot r) {
        r.telescope.liftMotor.set(0);
    }
}
