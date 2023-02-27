package frc.robot.behaviours;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Filesystem;
import frc.robot.V2d;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.stages.Stage;

public class AutoBehaviours {


    /*
    public static Consumer<Robot> alignTagTrigInit =
        r -> {
            r.autoData.sets.add(
                List.of(new goToAprilTagTrig(3, 24))
            );
        };



    public static Consumer<Robot> scoreAutoInit =
        r -> {
            r.telescope.resetLiftEncoder();
            r.telescope.resetRetractEncoder();

            r.autoData.sets.add(
                new Stage[] {
                    new grab(true),
                    new liftTo(5.5, .05),
                    new retractToPosition(8.4, .05)
                }
            );

            r.autoData.sets.add(new grab(false));
            r.autoData.sets.add(new timedPause(0.5));
            r.autoData.sets.add(new retractToPosition(0, .15)); // 6
        };


    public static Consumer<Robot> scoreAndBalanceAutoInit =
        r -> {
            scoreAutoInit.accept(r);

            TeleopBehaviours.teleOpInit.accept(r);
            r.telescope.resetRetractEncoder();
            r.positionData.reInit();

            r.autoData.sets.add(new goToPosition(new V2d(-0.8, -0.45), 0.3)); // 7
            r.autoData.sets.add(new timedPause(0.2));
            r.autoData.sets.add(new goToPosition(new V2d(-3, -0.45), 0.3));
            r.autoData.sets.add(new timedPause(0.2)); // 10
            r.autoData.sets.add(new goToAngle(90, 3));
            r.autoData.sets.add(new timedPause(0.2));
            r.autoData.sets.add(new goToAngle(180, 3)); // 13
            r.autoData.sets.add(new goToPosition(new V2d(-4.5, -0.45), 0.3));
            r.autoData.sets.add(new timedPause(0.2));
            r.autoData.sets.add(new liftTo(12.5, .15)); // 16
            r.autoData.sets.add(new retractToPosition(5, .05));
            r.autoData.sets.add(new grab(true));
            r.autoData.sets.add(new timedPause(0.2)); // 19
            r.autoData.sets.add(new retractToPosition(0, .15));
            r.autoData.sets.add(new timedPause(0.2));
            r.autoData.sets.add(new goToAngle(90, 3)); // 22
            r.autoData.sets.add(new goToAngle(0, 3));
        };
        */







    @SuppressWarnings ("unchecked")
    public static ArrayList<Stage[]> parseAutofile(String relativePath) {

        try (Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + "/" + relativePath))) {


            String line = "";

            ArrayList<Stage> set = new ArrayList<>();
            ArrayList<Stage[]> routine = new ArrayList<>();
            boolean inSet = false;

            int lineNmb = 0;

            try{

                while(s.hasNextLine()){

                    boolean stageRequired = true;

                    line = s.nextLine();
                    line = line.replaceAll("\t", " ");
                    line = line.trim();

                    if(line.indexOf("//") == 0 || line.equals("")){
                        lineNmb++;
                        continue; }



                    if(line.indexOf("#set") == 0) {

                        if(inSet) { throw new Exception("Cannot create a set within a set"); }
                        inSet = true;

                        lineNmb++;
                        continue;
                    }
                    else if(line.indexOf("#end") == 0) {

                        if(!inSet) { throw new Exception("Cannot end a nonexistant set"); }
                        inSet = false;

                        routine.add(Arrays.copyOf(set.toArray(), set.size(), Stage[].class));
                        set = new ArrayList<>();

                        lineNmb++;
                        continue;
                    }
                    else if(line.charAt(0) == '|') {
                        stageRequired = false;
                        line = line.substring(1);
                    }


                    int splitIdx = line.indexOf(" ");
                    if(splitIdx == -1) { throw new Exception("Bad line formatting"); }

                    String className = line.substring(0, splitIdx);
                    line = line.substring(splitIdx + 1);
                    line = line.trim();




                    // PARSE PARAMS
                    ArrayList<String> strArgs = new ArrayList<>();
                    while(true) {
                        int idx = line.indexOf(" ");
                        if(idx == -1) {
                            if(!line.equals("")) strArgs.add(line);
                            break;
                        }

                        strArgs.add(line.substring(0, idx));
                        line = line.substring(idx + 1);
                        line = line.trim();
                    }


                    // ATTEMPT MAKING AN OBJ
                    Class<?> cUnsafe = Class.forName("frc.robot.stages." + className);
                    if(!Stage.class.isAssignableFrom(cUnsafe)) {
                        throw new Exception("Invalid class name");
                    }

                    Class<? extends Stage> c = (Class<? extends Stage>)cUnsafe;

                    for(Constructor<?> con : c.getConstructors()) {

                        Class<?>[] argTypes = con.getParameterTypes();

                        ArrayList<Object> args = new ArrayList<>();
                        for(int i = 0; i < argTypes.length; i++) {

                            if     (argTypes[i].getName().equals("double")) { args.add(Double.valueOf(strArgs.get(i)).doubleValue()); }
                            else if(argTypes[i].getName().equals("boolean")) { args.add(Boolean.valueOf(strArgs.get(i)).booleanValue()); }
                            else if(argTypes[i].getName().equals("int")) { args.add(Integer.valueOf(strArgs.get(i)).intValue()); }
                            else if(argTypes[i].getName().equals("frc.robot.V2d")) {

                                int idx = strArgs.get(i).indexOf(",");
                                if(idx == -1) { throw new Exception("Your commas are fucked up"); }

                                double d1 = Double.valueOf(strArgs.get(i).substring(0, idx)).doubleValue();
                                double d2 = Double.valueOf(strArgs.get(i).substring(idx+1)).doubleValue();
                                args.add(new V2d(d1, d2));
                            }
                        }

                        set.add((Stage)con.newInstance((Object[])args.toArray()));
                        set.get(set.size() - 1).requireForSet = stageRequired;

                        if(inSet == false) {
                            routine.add(Arrays.copyOf(set.toArray(), set.size(), Stage[].class)); 
                            set = new ArrayList<>();
                        }
                    }

                    lineNmb++;
                } // end while

                if(set.size() != 0) {
                    routine.add(Arrays.copyOf(set.toArray(), set.size(), Stage[].class)); }

            } catch(Exception e) {

                telemetryUtil.logError("Error parsing file, line " + lineNmb + " \"" + relativePath + "\": " + e.toString(), Tabs.DEBUG);
                return null;
            };

            s.close();

            return routine;
        }
        catch(Exception e) {
            telemetryUtil.logError("Error loading file \"" + relativePath + "\": " + e.toString(), Tabs.DEBUG);
            return null;
        }

    }
}

