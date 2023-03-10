package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.robotUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.utils.V2d;

public class PneumaticBehaviors {

    static boolean coneFindingIsActive = false;
    static int coneFindingCounter = 0;


    static V2d[] limelightSpots = { new V2d(0, 0)};

    public static final Consumer<Robot> controlPneumatics = r -> {

        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }

        if (r.input.buttonPanel.getRawButtonPressed(2)|| r.input.driveController.getLeftBumperPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }


        if (r.input.armController.getXButtonPressed() || r.input.buttonPanel.getRawButtonPressed(1)){
            telescopeUtil.setArmPositionPID(ControlSettings.ARM_SAFE);
        }
    
        if (r.input.armController.getYButtonPressed()){
            telescopeUtil.setArmPositionPID(ControlSettings.HIGH_SCORE);
        }
    
        if (r.input.armController.getBButtonPressed()){
            telescopeUtil.setArmPositionPID(ControlSettings.IN_CONE);
            TurretBehaviors.turretPID.target = 2.7;
        }
        if (r.input.buttonPanel.getRawButtonPressed(6)){
            Robot.emergencyPIDstop = !Robot.emergencyPIDstop;
            telemetryUtil.put("Emergency PID stop", Robot.emergencyPIDstop, Tabs.ROBOT);

        }
        


    };

    
}
