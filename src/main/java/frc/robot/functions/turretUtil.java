package frc.robot.functions;

import frc.robot.subsystems.TurretData;

public class turretUtil {

    public void runTurretMotor(TurretData tdata, double speed){

        tdata.turretMotor.set(speed);
    
    }

    public void stopTurretMotor(TurretData tdata){

        tdata.turretMotor.stopMotor();

    }

}
