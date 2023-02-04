package frc.robot.functions;

import frc.robot.subsystems.TurretData;

public class turretUtil {

    public TurretData tdata = new TurretData();

    public void runTurretMotor(double speed){

        tdata.turretMotor.set(speed);
    
    }

    public void stopTurretMotor(){

        tdata.turretMotor.stopMotor();

    }

}
