package frc.robot.subsystems;



import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public final class InputData {

    public XboxController driveController;
    public XboxController armController;

    public Joystick buttonPanel;

    public Joystick joystick;



    public InputData() {
        driveController = new XboxController(Hardware.CONTROLLER_PORT);
        armController = new XboxController(Hardware.CONTROLLER_MECH_PORT);

        buttonPanel = new Joystick(Hardware.BUTTON_PANEL_PORT);

        joystick = new Joystick(Hardware.JOYSTICK_PORT);
    }


    public void sendTelemetry() {

        telemetryUtil.warnOn(!driveController.isConnected(), "Controller is disconnected (Port 0)", Tabs.DRIVER);
        telemetryUtil.warnOn(!armController.isConnected(), "Mechanism Controller is disconnected (Port 2)", Tabs.DRIVER);
        telemetryUtil.warnOn(!joystick.isConnected(), "Joystick is disconnected (Port 1)", Tabs.DRIVER);

    }
}
