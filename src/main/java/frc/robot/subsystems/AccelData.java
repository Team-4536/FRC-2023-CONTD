package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class AccelData {

    public Accelerometer accel = new BuiltInAccelerometer();

    public double lastXP = 0;
    public double lastXV = 0;

    public double lastYP = 0;
    public double lastYV = 0;

    public double currentXP = 0;
    public double currentXV = 0;
    public double currentXA = 0;

    public double currentYP = 0;
    public double currentYV = 0;
    public double currentYA = 0;

    public void update(double dt){

        lastXP = currentXP;
        lastXV = currentXV;

        lastYP = currentYP;
        lastYV = currentYV;

        currentYA = accel.getY();
        currentXA = accel.getX();

        currentXV = lastXV + currentXA * dt;
        currentYV = lastYV + currentYA * dt;

        currentXP = lastXP + currentXV * dt;
        currentYP = lastYP + currentYV * dt;

    }

    public void sendTelemetry(){

        telemetryUtil.put("X Accel", currentXA, Tabs.ROBOT);
        telemetryUtil.put("Y Accel", currentYA, Tabs.ROBOT);

        telemetryUtil.put("X Velo", currentXV, Tabs.ROBOT);
        telemetryUtil.put("Y Velo", currentYV, Tabs.ROBOT);

        telemetryUtil.put("X Pos", currentXP, Tabs.ROBOT);
        telemetryUtil.put("Y Pos", currentYP, Tabs.ROBOT);

    }
    
}
