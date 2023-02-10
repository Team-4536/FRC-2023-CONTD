package frc.robot.functions;

import frc.robot.subsystems.TurretData;

public class turretUtil {

    public static void runTurretMotor(TurretData tdata, double speed){

        tdata.turretMotor.set(speed);
    
    }

    public static void stopTurretMotor(TurretData tdata){

        tdata.turretMotor.stopMotor();

    }

}
