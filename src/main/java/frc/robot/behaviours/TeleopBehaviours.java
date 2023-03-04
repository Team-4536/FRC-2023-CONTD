package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.behaviours.subsystem.LiftBehaviors;
import frc.robot.behaviours.subsystem.RetractionBehaviors;
import frc.robot.behaviours.subsystem.TurretBehaviors;
import frc.robot.functions.driveUtil;
import frc.robot.functions.robotUtil;
import frc.robot.functions.pneumaticUtil;

public class TeleopBehaviours {


    // this burns my eyes
    public static Consumer<Robot> teleOpInit = r -> {

        robotUtil.stopRobot(r);

        r.telescope.resetRetractEncoder();
        r.telescope.resetLiftEncoder();
        r.turret.turretEncoder.setPosition(0);

        r.gyro.globGyroscope.reset();
        RetractionBehaviors.retractPID.reset();
        LiftBehaviors.liftPID.reset();
        TurretBehaviors.turretPID.reset();

        driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
        RetractionBehaviors.retractPID.target = r.telescope.retractVal();
        LiftBehaviors.liftPID.target = r.telescope.liftVal();
        TurretBehaviors.turretPID.target = r.turret.turretEncoder.getPosition();



        r.drive.FLEncoder.setPosition(0);
        r.drive.FREncoder.setPosition(0);
        r.drive.BLEncoder.setPosition(0);
        r.drive.BREncoder.setPosition(0);
    };

    public static Consumer<Robot> resetGyro = r -> {

        robotUtil.stopRobot(r);

        r.gyro.globGyroscope.reset();
        driveUtil.pid.target = r.gyro.globGyroscope.getAngle();

        pneumaticUtil.runSolenoid(r.brakes.brakeSolenoid, false);

        LiftBehaviors.liftPID.target = r.telescope.liftVal();
        RetractionBehaviors.retractPID.target = r.telescope.retractVal();
        TurretBehaviors.turretPID.target = r.turret.turretEncoder.getPosition();
    };


    public static Consumer<Robot> reInitOnButtonPress = r -> {

        if(r.input.driveController.getLeftBumperPressed()) {
            teleOpInit.accept(r); }
    };

}
