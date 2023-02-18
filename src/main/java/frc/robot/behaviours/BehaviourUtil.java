package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.*;



public class BehaviourUtil {

    @Hidden
    public static Consumer<Robot> stopDrive = r -> {
        driveUtil.stop(r.drive);
        r.telescope.retractMotor.set(0);
        r.telescope.liftMotor.set(0);
        r.turret.turretMotor.set(0);
    };

}
