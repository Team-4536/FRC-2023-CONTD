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

    }

    public void init() {
        globGyroscope.reset();
    }

}