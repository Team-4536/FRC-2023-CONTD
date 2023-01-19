package frc.robot.behaviours;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.stages.AutoStages;

public class AutoBehaviours {

    public static int stage = 0;
    public static ArrayList<Function<Robot, Boolean>> stages = new ArrayList<Function<Robot, Boolean>>();

    public static Consumer<Robot> autoPeriodic = r -> {

        if(stage == stages.size()) { 
            SmartDashboard.putBoolean("Auto running", false);
            BehaviourUtil.stopDrive.accept(r);
            return; 
        }

        Boolean x = stages.get(stage).apply(r);
        stage+= x?1:0;

        SmartDashboard.putBoolean("Auto running", true);
    };

    public static Consumer<Robot> turnLeftInit = r -> {
        stages.clear();
        stages.add(AutoStages.turnLeft90);
        stage = 0;
    };
}
