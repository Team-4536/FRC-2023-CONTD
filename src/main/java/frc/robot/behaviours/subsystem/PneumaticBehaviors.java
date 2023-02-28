package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.telescopeUtil;

public class PneumaticBehaviors {

    public static final Consumer<Robot> controlPneumatics = r -> {

        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }

        if (r.input.driveController.getLeftBumperPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }


            if (r.input.armController.getXButtonPressed()){
                telescopeUtil.setArmPositionPID(ControlSettings.ARM_SAFE);
            }
    
            if (r.input.armController.getYButtonPressed()){
                telescopeUtil.setArmPositionPID(ControlSettings.HIGH_SCORE);
            }
    
            if (r.input.armController.getBButtonPressed()){
                
            }

    };

    
}
