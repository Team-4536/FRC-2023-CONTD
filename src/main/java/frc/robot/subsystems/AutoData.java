package frc.robot.subsystems;

import java.util.ArrayList;

import frc.robot.Robot;
import frc.robot.functions.robotUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.stages.Stage;

public final class AutoData {

    public int setIdx = -1;
    public ArrayList<Stage[]> sets = new ArrayList<>();
    public boolean autoRunning = false;





    public void sendTelemetry() {
        telemetryUtil.put("Auto running", this.autoRunning, Tabs.DRIVER);
        telemetryUtil.put("Set progress", this.setIdx + "/" + this.sets.size(), Tabs.ROBOT);
    }



    public void reset(Robot r) {

        r.gyro.globGyroscope.reset();
        sets.clear();
        setIdx = -1;
    }




    public void update(Robot r) {

        if(sets.size() == 0 || setIdx == sets.size() ) {
            robotUtil.stopRobot(r);
            autoRunning = false;
            return;
        }

        if(setIdx == -1) {
            setIdx++;
            for(Stage s : sets.get(0)) { s.init(); };
        }


        Boolean stageFinished = true;
        for(Stage s : sets.get(setIdx)) {
            if(s.requireForSet == false) { s.run(r); }
            else if(s.run(r) == false) { stageFinished = false; }
        }


        if(stageFinished) {

            for(Stage s : sets.get(setIdx)) { s.end(r); };
            setIdx++;

            if(setIdx != sets.size()) {
                for(Stage s : sets.get(setIdx)) { s.init(); };
            }
        }

    }

}
