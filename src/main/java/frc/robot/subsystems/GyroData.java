package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class GyroData {

    public final AHRS globGyroscope = new AHRS();
    public final AHRS localGyroscope = new AHRS();

    public void sendTelemetry(){

        telemetryUtil.put("Global Angle", globGyroscope.getAngle(), Tabs.ROBOT);
        telemetryUtil.put("Local Angle", localGyroscope.getAngle(), Tabs.ROBOT);

    }

}