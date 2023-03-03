package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class GyroData {

    public final AHRS globGyroscope = new AHRS();

    public double getYaw() {
        return this.globGyroscope.getAngle();
    }

    public void sendTelemetry(){

        telemetryUtil.put("Angle", globGyroscope.getAngle(), Tabs.ROBOT);

        telemetryUtil.put("y", globGyroscope.getYaw(), Tabs.ROBOT);
        telemetryUtil.put("p", globGyroscope.getPitch(), Tabs.ROBOT);
        telemetryUtil.put("r", globGyroscope.getRoll(), Tabs.ROBOT);

    }

    public void init() {
        globGyroscope.reset();
    }

}