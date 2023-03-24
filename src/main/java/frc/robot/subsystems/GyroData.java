package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.Robot;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class GyroData {

    public final AHRS globGyroscope = new AHRS();

    public ADXRS450_Gyro armGyro = new ADXRS450_Gyro();

    public GyroData(){
        armGyro.calibrate();
    }

    public double getYaw() {
        return this.globGyroscope.getAngle();
    }

    public void sendTelemetry(){

        telemetryUtil.put("Angle", globGyroscope.getAngle(), Tabs.ROBOT);

        telemetryUtil.put("y", globGyroscope.getYaw(), Tabs.ROBOT);
        telemetryUtil.put("p", globGyroscope.getPitch(), Tabs.ROBOT);
        telemetryUtil.put("r", globGyroscope.getRoll(), Tabs.ROBOT);

        telemetryUtil.put("arm angle", armGyro.getAngle(), Tabs.ROBOT);
        telemetryUtil.put("sarm angle", armGyro.getRotation2d().getDegrees(), Tabs.ROBOT);

        if (Robot.instance.telescope.upBound.get()){
            armGyro.reset();
        }

    }

    public void init() {
        globGyroscope.reset();
    }

}