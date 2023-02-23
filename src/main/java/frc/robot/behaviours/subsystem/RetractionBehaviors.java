package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.inputUtil;

public class RetractionBehaviors {

    public static final Consumer<Robot> controlRetractionUnbounded = r -> {
        r.telescope.retractMotor.set(
            inputUtil.deadzoneAxis(
                r.input.armController.getLeftY(),
                ControlSettings.MOVEMENT_DEADZONE
            ) * ControlSettings.RETRACTION_MULT
        );
    };
    
}
