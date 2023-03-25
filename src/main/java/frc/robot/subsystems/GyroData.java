package frc.robot.subsystems;

import org.ejml.dense.row.decomposition.svd.SafeSvd_DDRM;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class GyroData {

    public final AHRS globGyroscope = new AHRS();
    public final AHRS armHelper = new AHRS();

    public ADXRS450_Gyro armGyro = new ADXRS450_Gyro();

    public Timer sackettEmmett = new Timer();

    public double trouble = 0;

    public GyroData(){
        armGyro.calibrate();
    }

    public double getYaw() {
        return this.globGyroscope.getAngle();
    }

    public double getArm(){
        return armGyro.getAngle() + armHelper.getAngle()/12 + trouble;
    }

    public void resetArmAngle(){
        trouble = -armGyro.getAngle() - armHelper.getAngle()/12;
    }

    public void sendTelemetry(){

        telemetryUtil.put("Angle", globGyroscope.getAngle(), Tabs.ROBOT);
        telemetryUtil.put("helper angle", armHelper.getAngle(), Tabs.ROBOT);

        telemetryUtil.put("y", globGyroscope.getYaw(), Tabs.ROBOT);
        telemetryUtil.put("p", globGyroscope.getPitch(), Tabs.ROBOT);
        telemetryUtil.put("r", globGyroscope.getRoll(), Tabs.ROBOT);

        telemetryUtil.put("arm angle", getArm(), Tabs.ROBOT);
        telemetryUtil.put("sarm angle", armGyro.getAngle(), Tabs.ROBOT);

        if (Robot.instance.telescope.upBound.get()){
            sackettEmmett.start();
        }
        if (!Robot.instance.telescope.upBound.get()){
            sackettEmmett.stop();
            sackettEmmett.reset();
        }
        if (sackettEmmett.get() >= .05){
            resetArmAngle();
        }

    }

    public void init() {
        globGyroscope.reset();
    }

}