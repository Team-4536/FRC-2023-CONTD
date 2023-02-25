package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.stages.timedPause;
import frc.robot.stages.goToAngle;
import frc.robot.stages.goToAprilTagTrig;
import frc.robot.stages.goToPosition;
import frc.robot.stages.grab;
import frc.robot.stages.liftTo;
import frc.robot.stages.moveTimed;
import frc.robot.stages.retractToPosition;

public class AutoBehaviours {


    public static Consumer<Robot> turn180init =
        r -> { r.autoData.stages.add(new goToAngle(180, 1)); };

    public static Consumer<Robot> alignTagTrigInit =
        r -> { r.autoData.stages.add(new goToAprilTagTrig(3, 24)); };

    public static Consumer<Robot> goToPosition0Init =
        r -> { r.autoData.stages.add(new goToPosition(new V2d(0, 0), 0.3)); };

    public static Consumer<Robot> testTimedAuto =
        r -> { r.autoData.stages.add(new moveTimed(1, new V2d(0, .14), .5));
               r.autoData.stages.add(new timedPause(.5));
               r.autoData.stages.add(new moveTimed(1, new V2d(0, -.14), .5)); };

    public static Consumer<Robot> testOdometryAuto =
        r -> {

            TeleopBehaviours.teleOpInit.accept(r);
            r.telescope.resetRetractEncoder();
            r.positionData.reInit();
            r.autoData.stages.add(new goToPosition(new V2d(1, 0), 0.2));
            r.autoData.stages.add(new timedPause(0.5));
            r.autoData.stages.add(new grab(false));
            r.autoData.stages.add(new retractToPosition(8, .05));
            r.autoData.stages.add(new grab(true));
            r.autoData.stages.add(new timedPause(1));
            r.autoData.stages.add(new retractToPosition(0, .05));
            r.autoData.stages.add(new goToPosition(new V2d(0, 0), 0.2));
            };

    public static Consumer<Robot> retractAuto =
        r -> { r.telescope.resetLiftEncoder();
               r.telescope.resetRetractEncoder();
               r.autoData.stages.add(new grab(true));
               r.autoData.stages.add(new liftTo(5.5, .05));
               r.autoData.stages.add(new timedPause(0.5));
               r.autoData.stages.add(new retractToPosition(8.4, .05));
               r.autoData.stages.add(new grab(false));
               r.autoData.stages.add(new timedPause(0.5));
               r.autoData.stages.add(new retractToPosition(0, .15)); // 6
             };

    public static Consumer<Robot> scoreAndBalanceAuto = 
        r -> {
            retractAuto.accept(r);

            TeleopBehaviours.teleOpInit.accept(r);
            r.telescope.resetRetractEncoder();
            r.positionData.reInit();

            r.autoData.stages.add(new goToPosition(new V2d(-0.8, -0.45), 0.3)); // 7
            r.autoData.stages.add(new timedPause(0.2));
            r.autoData.stages.add(new goToPosition(new V2d(-3, -0.45), 0.3));
            r.autoData.stages.add(new timedPause(0.2)); // 10
            r.autoData.stages.add(new goToAngle(90, 3));
            r.autoData.stages.add(new timedPause(0.2));
            r.autoData.stages.add(new goToAngle(180, 3)); // 13
            r.autoData.stages.add(new goToPosition(new V2d(-4.5, -0.45), 0.3));
            r.autoData.stages.add(new timedPause(0.2));
            r.autoData.stages.add(new liftTo(12.5, .15)); // 16
            r.autoData.stages.add(new retractToPosition(5, .05));
            r.autoData.stages.add(new grab(true));
            r.autoData.stages.add(new timedPause(0.2)); // 19
            r.autoData.stages.add(new retractToPosition(0, .15));
            r.autoData.stages.add(new timedPause(0.2));
            r.autoData.stages.add(new goToAngle(90, 3)); // 22
            r.autoData.stages.add(new goToAngle(0, 3));
            
        };

}

