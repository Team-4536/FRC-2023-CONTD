package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.Constants;

public final class InputData {

    public XboxController controller;
    public Joystick joystick;



    public InputData() {
        controller = new XboxController(Constants.CONTROLLER_PORT);
        joystick = new Joystick(Constants.JOYSTICK_PORT);
    }


    public void sendTelemetry() {

        telemetryUtil.warnOn(!controller.isConnected(), "Controller is disconnected (Port 0)", Tabs.DRIVER);
        telemetryUtil.warnOn(!joystick.isConnected(), "Joystick is disconnected (Port 1)", Tabs.DRIVER);

    }
}
