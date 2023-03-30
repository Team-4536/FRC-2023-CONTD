package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.turretUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.utils.V2d;

public class PneumaticBehaviors {

    static boolean coneFindingIsActive = false;
    static int coneFindingCounter = 0;


    static V2d[] limelightSpots = { new V2d(0, 0)};

    public static final Consumer<Robot> controlPneumatics = r -> {


        // grabber actuation control - A button on arm controller
        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }


        // brake actuation control - B button on drive controller OR button panel
        if (r.input.buttonPanelL2() || r.input.driveController.getBButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }


        // low cone arm setpoint - X button on arm controller OR button panel 
        if (r.input.armController.getXButtonPressed() || r.input.buttonPanelR2()){
            telescopeUtil.setArmPositionPID(ControlSettings.LOW_CONE); 
            TurretBehaviors.turretPID.target = 6.14; }


        
        if (r.input.buttonPanel.getRawButtonPressed(2)){
            telescopeUtil.setArmPositionPID(ControlSettings.LOW_CONE); 
            TurretBehaviors.turretPID.target = -4.5; }
    

        // double substation arm setpoint - Y button on arm controller OR button panel
        if (r.input.armController.getYButtonPressed() || r.input.buttonPanel.getRawButtonPressed(3)){
            telescopeUtil.setArmPositionPID(ControlSettings.DOUBLE_SUB); 
            TurretBehaviors.turretPID.target = 0; }
    

        // high cone arm setpoint - B button on arm controller OR button panel
        if (r.input.armController.getBButtonPressed() || r.input.buttonPanel.getRawButtonPressed(6)){
            telescopeUtil.setArmPositionPID(ControlSettings.HIGH_CONE); 
            TurretBehaviors.turretPID.target = 4.57; }

    
        if (r.input.buttonPanel.getRawButtonPressed(4)){
            telescopeUtil.setArmPositionPID(ControlSettings.HIGH_CONE); 
            TurretBehaviors.turretPID.target = -4.57; }


        // safe arm setpoint - button panel
        if (r.input.buttonPanel.getRawButtonPressed(5)){
            telescopeUtil.setArmPositionPID(ControlSettings.ARM_RESET);
            TurretBehaviors.turretPID.target = 0; }


        // emergency PID stop button - button panel
        if (r.input.buttonPanel.getRawButtonPressed(7)){
            Robot.emergencyPIDstop = !Robot.emergencyPIDstop;
            telemetryUtil.put("Emergency PID stop", Robot.emergencyPIDstop, Tabs.ROBOT); }
        


    };

    
}
