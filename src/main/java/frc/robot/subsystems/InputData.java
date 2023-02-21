package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public final class InputData {

    public XboxController controller;
    public XboxController controllerMech;
    public Joystick joystick;



    public InputData() {
        controller = new XboxController(Hardware.CONTROLLER_PORT);
        controllerMech = new XboxController(Hardware.CONTROLLER_MECH_PORT);
        joystick = new Joystick(Hardware.JOYSTICK_PORT);
    }


    public void sendTelemetry() {

        telemetryUtil.warnOn(!controller.isConnected(), "Controller is disconnected (Port 0)", Tabs.DRIVER);
        telemetryUtil.warnOn(!controllerMech.isConnected(), "Mechanism Controller is disconnected (Port 2)", Tabs.DRIVER);
        telemetryUtil.warnOn(!joystick.isConnected(), "Joystick is disconnected (Port 1)", Tabs.DRIVER);

    }
}
