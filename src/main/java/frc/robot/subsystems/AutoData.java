package frc.robot.subsystems;

import java.util.ArrayList;

import frc.robot.Robot;
import frc.robot.functions.robotUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.stages.Stage;

public final class AutoData {

    public int stage = -1;
    public ArrayList<Stage> stages = new ArrayList<Stage>();
    public boolean autoRunning = false;




    public void sendTelemetry() {
        telemetryUtil.put("Auto running", this.autoRunning, Tabs.DRIVER);
        telemetryUtil.put("Stage progress", this.stage + "/" + this.stages.size(), Tabs.ROBOT);
    }



    public void reset(Robot r) {

        r.gyro.globGyroscope.reset();
        stages.clear();
        stage = -1;
    }




    public void update(Robot r) {

        if(stages.size() == 0 || stage == stages.size() ) {
            robotUtil.stopRobot(r);
            autoRunning = false;
            return;
        }

        if(stage == -1) { stage++; stages.get(0).init(); }


        Boolean stageFinished = stages.get(stage).run(r);
        if(stageFinished) {
            stage++;

            if(stage != stages.size())
                stages.get(stage).init();
        }

    }

}
