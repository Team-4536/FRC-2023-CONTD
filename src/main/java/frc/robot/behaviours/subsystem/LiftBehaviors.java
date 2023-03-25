package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.utils.inputUtil;


public class LiftBehaviors {

    public static PIDController liftPID = new PIDController(0.4, 0.01, 0);
    public static PIDController gyroPID = new PIDController(0.09, 0.00001, 0);

    public static final Consumer<Robot> controlLiftUnboundedPID = r -> {

        liftPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt * ControlSettings.LIFT_PID_SETPOINT_COMPOUND_COEFFICIENT;

        if (liftPID.target < ControlSettings.LIFT_ENCODER_MINIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MINIMUM; }
        if (liftPID.target > ControlSettings.LIFT_ENCODER_MAXIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MAXIMUM; }

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);

        //telemetryUtil.put(" lift pid raw output", PIDOut, Tabs.DEBUG);

        if (Math.abs(PIDOut) > ControlSettings.LIFT_MOTOR_MAX_OUTPUT) { PIDOut = PIDOut * (ControlSettings.LIFT_MOTOR_MAX_OUTPUT/Math.abs(PIDOut)); }

        //telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        //telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);

        PIDOut += -r.input.armController.getRightY() * ControlSettings.LIFT_PID_USER_MULTIPLIER;

        telescopeUtil.softHardLimitLift(r.telescope, PIDOut);


    };


    public static final Consumer<Robot> controlLiftUnbounded = r -> {

        double pwr = inputUtil.deadzoneStick(-r.input.armController.getRightY())
            * ControlSettings.LIFT_MULT * 6;

        r.telescope.liftMotor.set(pwr);
    };

    public static final Consumer<Robot> gyroPIDControl = r -> {

        gyroPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt * ControlSettings.LIFT_GYRO_SETPOINT_COMPOUND_COEFFICIENT;

        if (gyroPID.target < ControlSettings.LIFT_GYRO_MINIMUM){ gyroPID.target = ControlSettings.LIFT_GYRO_MINIMUM; }
        if (gyroPID.target > ControlSettings.LIFT_GYRO_MAXIMUM){ gyroPID.target = ControlSettings.LIFT_GYRO_MAXIMUM; }

        double PIDOut = -gyroPID.tick(r.gyro.armGyro.getAngle(), Robot.dt, false);

        telemetryUtil.put(" lift pid raw output", PIDOut, Tabs.DEBUG);

        //if (Math.abs(PIDOut) > maxOutput) { PIDOut = PIDOut * (maxOutput/Math.abs(PIDOut)); }
        if (PIDOut < -.4) { PIDOut = -.4; }
        if (PIDOut > ControlSettings.LIFT_MOTOR_MAX_OUTPUT) {PIDOut = ControlSettings.LIFT_MOTOR_MAX_OUTPUT;}

        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", gyroPID.target, Tabs.DEBUG);

        PIDOut += -r.input.armController.getRightY() * ControlSettings.GYRO_PID_USER_MULTIPLIER;

        telescopeUtil.softHardLimitLift(r.telescope, PIDOut);


    };
    
}
