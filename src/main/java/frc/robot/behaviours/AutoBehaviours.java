package frc.robot.behaviours;

import java.util.ArrayList;
import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.stages.AutoStages;
import frc.robot.stages.Stage;

public class AutoBehaviours {

    public static int stage = 0;
    public static ArrayList<Stage> stages = new ArrayList<Stage>();


    public static Consumer<Robot> autoPeriodic = r -> {

        if(stage == stages.size()) {
            SmartDashboard.putBoolean("Auto running", false);
            BehaviourUtil.stopDrive.accept(r);
            return;
        }

        Boolean x = stages.get(stage).run(r);
        stage+= x?1:0;

        SmartDashboard.putBoolean("Auto running", true);
    };

    public static void resetAuto(Robot r) {

        r.gyro.globGyroscope.reset();
        stages.clear();
        stage = 0;
    }






    public static Consumer<Robot> turn180init = r -> {

        resetAuto(r);
        stages.add(new AutoStages.goTo180());
    };

    public static Consumer<Robot> alignTagTrigInit = r -> {

        resetAuto(r);

        stages.add(new AutoStages.goToAprilTagTrig(3, 24));
    };

    public static Consumer<Robot> balenceWithGyroInit = r -> {

        resetAuto(r);
        stages.add(new AutoStages.balenceWithGyro());
    };

    public static Consumer<Robot> goToCone = r -> {

        resetAuto(r);
        stages.add(new AutoStages.goToCone(9, -11, -16));
    };
}