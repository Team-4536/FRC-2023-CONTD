package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.turretUtil;
import frc.robot.utils.inputUtil;

public class TurretBehaviors {

    public static boolean glymer = false;

    public static final Consumer<Robot> controlTurretBounded = r -> {

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightX())
            * ControlSettings.TURRET_MULT;

        turretUtil.run(r.turret, flymer);
    };

    public static final Consumer<Robot> controlTurretBoundedPID = r -> {

        if (r.input.armController.getLeftBumperPressed()){
            glymer = !glymer;
        }

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightX())
            * ControlSettings.TURRET_MULT;

        if (glymer){
            flymer = flymer * -1;
        }

        turretUtil.run(r.turret, flymer);

        if (r.turret.cwBound.get()){
            r.turret.turretEncoder.setPosition(0);
        }

    };
    
}
