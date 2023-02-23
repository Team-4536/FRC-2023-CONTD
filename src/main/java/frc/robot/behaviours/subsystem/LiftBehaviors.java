package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.inputUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;


public class LiftBehaviors {

    public static PIDController liftPID = new PIDController(0.4, 0.01, 0);

    public static final Consumer<Robot> controlLiftUnboundedPID = r -> {

        liftPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt;

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);
        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);

        r.telescope.liftMotor.set(PIDOut);
    };


    public static final Consumer<Robot> controlLiftUnbounded = r -> {

        double pwr = inputUtil.deadzoneStick(-r.input.armController.getRightY())
            * ControlSettings.LIFT_MULT;

        r.telescope.liftMotor.set(pwr);
    };
    
}
