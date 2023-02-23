package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.behaviours.subsystem.LiftBehaviors;
import frc.robot.functions.driveUtil;
import frc.robot.functions.robotUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TeleopBehaviours {


    // this burns my eyes
    public static Consumer<Robot> teleOpInit = r -> {

        robotUtil.stopRobot(r);

        r.telescope.liftEncoder.setPosition(100);
        r.telescope.retractEncoder.setPosition(100);

        r.gyro.globGyroscope.reset();
        driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
        LiftBehaviors.liftPID.target = r.telescope.liftVal();

        telemetryUtil.debugLog("LOG", Tabs.DEBUG);
    };

}
