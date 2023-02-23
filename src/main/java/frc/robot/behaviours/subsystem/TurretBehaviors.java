package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.inputUtil;
import frc.robot.functions.turretUtil;

public class TurretBehaviors {

    public static final Consumer<Robot> controlTurretBounded = r -> {

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightX())
            * ControlSettings.TURRET_MULT;

        turretUtil.run(r.turret, flymer);
    };
    
}
