
package frc.robot.stages;

import java.util.function.Function;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;

public class AutoStages {

    public static Function<Robot, Boolean> turnLeft90 = r -> {

        driveUtil.setPowerMechanum(r.drive, 0, 0, 0.1, 0.5);

        if(r.gyro.globGyroscope.getAngle() >= 87 && r.gyro.globGyroscope.getAngle() <= 93){
            return true;
        }
        return false;
    };
}