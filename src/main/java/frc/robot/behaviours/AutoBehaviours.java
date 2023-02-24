package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.V2d;
import frc.robot.stages.timedPause;
import frc.robot.stages.goToAngle;
import frc.robot.stages.goToAprilTagTrig;
import frc.robot.stages.goToPosition;
import frc.robot.stages.grab;
import frc.robot.stages.moveTimed;
import frc.robot.stages.retractToPosition;

public class AutoBehaviours {


    public static Consumer<Robot> turn180init =
        r -> { r.autoData.stages.add(new goToAngle(180, 1)); };

    public static Consumer<Robot> alignTagTrigInit =
        r -> { r.autoData.stages.add(new goToAprilTagTrig(3, 24)); };

    public static Consumer<Robot> goToPosition0Init =
        r -> { r.autoData.stages.add(new goToPosition(new V2d(0, 0))); };

    public static Consumer<Robot> testTimedAuto =
        r -> { r.autoData.stages.add(new moveTimed(1, new V2d(0, .14), .5));
               r.autoData.stages.add(new timedPause(.5));
               r.autoData.stages.add(new moveTimed(1, new V2d(0, -.14), .5)); };

    public static Consumer<Robot> testOdometryAuto =
        r -> {

            TeleopBehaviours.teleOpInit.accept(r);
            r.telescope.resetRetractEncoder();
            r.positionData.reInit();
            r.autoData.stages.add(new goToPosition(new V2d(1, 0)));
            r.autoData.stages.add(new timedPause(0.5));
            r.autoData.stages.add(new grab(false));
            r.autoData.stages.add(new retractToPosition(8, .05));
            r.autoData.stages.add(new grab(true));
            r.autoData.stages.add(new timedPause(1));
            r.autoData.stages.add(new retractToPosition(0, .05));
            r.autoData.stages.add(new goToPosition(new V2d(0, 0)));
            // r.autoData.stages.add(new goToPosition(new V2d(0, -1)));
            // r.autoData.stages.add(new timedPause(0.5));
            // r.autoData.stages.add(new goToPosition(new V2d(0, 0)));
            // r.autoData.stages.add(new timedPause(0.5));
            // r.autoData.stages.add(new goToPosition(new V2d(1, -1)));
            };
            
    public static Consumer<Robot> retractAuto =
        r -> { r.telescope.retractEncoder.setPosition(100);
               r.autoData.stages.add(new grab(false));
               r.autoData.stages.add(new retractToPosition(8, .05));
               r.autoData.stages.add(new grab(true));
               r.autoData.stages.add(new timedPause(1));
               r.autoData.stages.add(new retractToPosition(0, .03)); };

}

