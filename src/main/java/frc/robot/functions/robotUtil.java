package frc.robot.functions;

import frc.robot.Robot;

public final class robotUtil {

    public static void stopRobot(Robot r) {

        driveUtil.stop(r.drive);
        r.telescope.retractMotor.set(0);
        r.telescope.liftMotor.set(0);
        r.turret.turretMotor.set(0);
    }
}
