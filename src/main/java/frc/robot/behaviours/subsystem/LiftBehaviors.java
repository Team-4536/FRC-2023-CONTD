package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.inputUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.telemetryUtil.Tabs;


public class LiftBehaviors {

    public static PIDController liftPID = new PIDController(0.4, 0.01, 0);

    public static final Consumer<Robot> controlLiftUnboundedPID = r -> {

        liftPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt * ControlSettings.LIFT_PID_SETPOINT_COMPOUND_COEFFICIENT;

        if (liftPID.target < ControlSettings.LIFT_ENCODER_MINIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MINIMUM; }
        if (liftPID.target > ControlSettings.LIFT_ENCODER_MAXIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MAXIMUM; }

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);

        telemetryUtil.put(" lift pid raw output", PIDOut, Tabs.DEBUG);

        if (Math.abs(PIDOut) > ControlSettings.LIFT_MOTOR_MAX_OUTPUT) { PIDOut = PIDOut * (ControlSettings.LIFT_MOTOR_MAX_OUTPUT/Math.abs(PIDOut)); }

        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);

        PIDOut += -r.input.armController.getRightY() * ControlSettings.LIFT_PID_USER_MULTIPLIER;

        telescopeUtil.softHardLimitLift(r.telescope, PIDOut);

        if (r.input.armController.getXButtonPressed()){
            liftPID.target = 6;
        }

    };


    public static final Consumer<Robot> controlLiftUnbounded = r -> {

        double pwr = inputUtil.deadzoneStick(-r.input.armController.getRightY())
            * ControlSettings.LIFT_MULT * 6;

        r.telescope.liftMotor.set(pwr);
    };
    
}
