package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.controllers.PIDController;
import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.turretUtil;
import frc.robot.utils.inputUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.functions.telemetryUtil;

public class TurretBehaviors {

    public static boolean glymer = false;

    public static PIDController turretPID = new PIDController(.13, 0.004, 0);

    public static final Consumer<Robot> controlTurretBounded = r -> {

        if (r.input.armController.getLeftBumperPressed()){
            glymer = !glymer;
        }

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightTriggerAxis() - r.input.armController.getLeftTriggerAxis())
            * ControlSettings.TURRET_MULT;

            
            if (glymer){
                flymer = flymer * -1;
            }

        turretUtil.run(r.turret, flymer);
    };

    public static final Consumer<Robot> controlTurretBoundedPID = r -> {

        turretPID.target += inputUtil.deadzoneStick(r.input.armController.getRightX()) * Robot.dt * ControlSettings.TURRET_PID_SETPOINT_COMPOUND_COEFFICIENT;

        if (turretPID.target < ControlSettings.TURRET_ENCODER_MINIMUM){ turretPID.target = ControlSettings.TURRET_ENCODER_MINIMUM; }
        if (turretPID.target > ControlSettings.TURRET_ENCODER_MAXIMUM){ turretPID.target = ControlSettings.TURRET_ENCODER_MAXIMUM; }

        double PIDOut = turretPID.tick(r.turret.turretEncoder.getPosition(), Robot.dt, false);

        telemetryUtil.put("pid raw output", PIDOut, Tabs.DEBUG);

        if (Math.abs(PIDOut) > ControlSettings.TURRET_MOTOR_MAX_OUTPUT) { PIDOut = PIDOut * (ControlSettings.TURRET_MOTOR_MAX_OUTPUT/Math.abs(PIDOut)); }

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightX())
            * ControlSettings.TURRET_MULT;

        telemetryUtil.put("turret target", turretPID.target, Tabs.DEBUG);

        PIDOut += r.input.armController.getRightX() * ControlSettings.TURRET_PID_USER_MULTIPLIER;

        turretUtil.run(r.turret, PIDOut);

        if (r.turret.cwBound.get()){
            //r.turret.turretEncoder.setPosition(0);
        }

    };

    
}
