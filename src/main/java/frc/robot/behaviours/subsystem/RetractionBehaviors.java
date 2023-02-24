package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.controllers.PIDController;
import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.inputUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class RetractionBehaviors {

    public static PIDController retractPID = new PIDController(.5, .01, 0);

    public static final Consumer<Robot> controlRetractionUnbounded = r -> {

        r.telescope.retractMotor.set(
            inputUtil.deadzoneAxis(
                r.input.armController.getLeftY(),
                ControlSettings.MOVEMENT_DEADZONE
            ) * ControlSettings.RETRACTION_MULT
        ); 
    };

    public static final Consumer<Robot> controlRetractUnboundedPID = r -> {

        retractPID.target += -inputUtil.deadzoneStick(r.input.armController.getLeftY()) * Robot.dt * ControlSettings.RETRACT_PID_SETPOINT_COMPOUND_COEFFICIENT;

        if (retractPID.target < ControlSettings.RETRACT_ENCODER_MINIMUM){ retractPID.target = ControlSettings.RETRACT_ENCODER_MINIMUM; }
        if (retractPID.target > ControlSettings.RETRACT_ENCODER_MAXIMUM){ retractPID.target = ControlSettings.RETRACT_ENCODER_MAXIMUM; }

        double PIDOut = -retractPID.tick(r.telescope.retractVal(), Robot.dt, false);

        telemetryUtil.put("pid raw output", PIDOut, Tabs.DEBUG);

        if (Math.abs(PIDOut) > ControlSettings.RETRACT_MOTOR_MAX_OUTPUT) { PIDOut = PIDOut * (ControlSettings.RETRACT_MOTOR_MAX_OUTPUT/Math.abs(PIDOut)); }

        telemetryUtil.put("retract target", retractPID.target, Tabs.DEBUG);
        telemetryUtil.put("pid output", PIDOut, Tabs.DEBUG);

        PIDOut += r.input.armController.getLeftY() * ControlSettings.RETRACT_PID_USER_MULTIPLIER;

        telescopeUtil.softLimitRetract(r.telescope, PIDOut);

        if (r.input.armController.getBButtonPressed()){
            retractPID.target = 8;
        }

        if (r.input.armController.getYButtonPressed()){
            retractPID.target = 0;
        }

    };
    
}
