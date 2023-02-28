package frc.robot.stages;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.pneumaticUtil;


public final class balanceWithGyro extends Stage {
    double motorPower;
    double STOP_RANGE = 2;

    Timer flymer = new Timer();
    
    public balanceWithGyro(double motorPower) {
        this.motorPower = motorPower;
    }

    @Override public boolean run(Robot r) {
        
        if(r.gyro.globGyroscope.getPitch() > STOP_RANGE){
            flymer.stop();
            flymer.reset();
            pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, false);
            driveUtil.setPowerMechPID(
                r,
                motorPower,
                 0,
             0.8);
        }
        if(r.gyro.globGyroscope.getPitch() < -STOP_RANGE){
            flymer.stop();
            flymer.reset();
            pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, false);
            driveUtil.setPowerMechPID(
                r,
                -motorPower,
                 0,
             0.8);
        }
        if(Math.abs(r.gyro.globGyroscope.getPitch()) < STOP_RANGE){
            pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, true);
            if(flymer.get() == 0){
                flymer.start();
            }    
        }
        
        return flymer.get() > 2;

    }

}