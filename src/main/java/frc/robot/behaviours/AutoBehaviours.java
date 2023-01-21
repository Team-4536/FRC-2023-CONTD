package frc.robot.behaviours;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static Consumer<Robot> turnLeftInit = r -> {

        r.gyro.globGyroscope.reset();

        stages.clear();
        stages.add(new AutoStages.goTo90());
        stage = 0;
    };

    public static Consumer<Robot> alignTagInit = r -> {

        r.gyro.globGyroscope.reset();

        stages.clear();
        stages.add(new AutoStages.goToAprilTag(1));
        stage = 0;
    };
}
