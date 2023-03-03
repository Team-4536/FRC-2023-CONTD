package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.controllers.PIDController;
import frc.robot.functions.driveUtil;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

import java.lang.Math;


public final class balanceWithGyro extends Stage {
    double STOP_RANGE = 1;
    PIDController yPID;
    double yMove;
    
    Timer flymer = new Timer();
    
    public balanceWithGyro(double Pvalue) {
        this.yPID = new PIDController(Pvalue, 0, -.025);
        yPID.target = 0;
    }

    @Override public boolean run(Robot r) {
        yMove = -yPID.tick(r.gyro.globGyroscope.getRoll(), Robot.dt, false);
        if(Math.abs(r.gyro.globGyroscope.getRoll()) > STOP_RANGE){
            flymer.stop();
            flymer.reset();
            pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, false);
            driveUtil.setPowerMechPID(
                r,
                0,
                yMove,
             0.8);
        }
        if(Math.abs(r.gyro.globGyroscope.getRoll()) < STOP_RANGE){
            pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, true);
            if(flymer.get() == 0){
                flymer.start();
            }    
        }

        telemetryUtil.put("balls", flymer.get(), Tabs.DEBUG);
        
        return flymer.get() > 2;

    }

}