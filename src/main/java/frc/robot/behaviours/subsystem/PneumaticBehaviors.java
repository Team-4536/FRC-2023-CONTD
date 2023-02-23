package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.pneumaticUtil;

public class PneumaticBehaviors {

    public static final Consumer<Robot> controlPneumatics = r -> {

        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }

        if (r.input.driveController.getLeftBumperPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }
    };
    
}
