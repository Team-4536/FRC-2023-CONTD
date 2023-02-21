package frc.robot.functions;

import frc.robot.subsystems.TurretData;

public class turretUtil {

    public static void runTurretMotor(TurretData tdata, double speed){

        tdata.turretMotor.set(speed);
    }

    public static void stopTurretMotor(TurretData tdata){

        tdata.turretMotor.stopMotor();
    }

    public static void run(TurretData tdata, double speed){

        if (tdata.ccwBound.get()){

            if (speed < 0) { speed = 0; }
            tdata.turretMotor.set(speed);

        } else if (tdata.cwBound.get()){

            if (speed > 0) { speed = 0;}
            tdata.turretMotor.set(speed);

        } else {

            tdata.turretMotor.set(speed);
        }
    }

}
