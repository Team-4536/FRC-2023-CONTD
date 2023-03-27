package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.controllers.PIDController;
import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.turretUtil;
import frc.robot.utils.inputUtil;

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

    
}
