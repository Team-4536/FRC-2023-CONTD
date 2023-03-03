package frc.robot.functions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.IntegerPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.behaviours.annotations.Hidden;






class DashFuncChooser {
    SendableChooser<String> chooser;
    String name;
    Consumer<Consumer<Robot>> setTarget;

    public DashFuncChooser(String n, Consumer<Consumer<Robot>> t) {
        this.chooser = new SendableChooser<String>();
        this.name = n;
        this.setTarget = t;
    };
}

class StringChooser {

    SendableChooser<String> chooser;
    String name;
    Consumer<String> setFunc;
    String prev = "";

    public StringChooser(String n, ArrayList<String> opts, Consumer<String> set) {
        this.chooser = new SendableChooser<String>();
        this.name = n;
        this.setFunc = set;

        this.chooser.setDefaultOption(opts.get(0), opts.get(0));

        for(int i = 1; i < opts.size(); i++) {
            this.chooser.addOption(opts.get(i), opts.get(i)); }
    };
}









public class telemetryUtil {


    public static void warnOn(boolean condition, String title, Tabs tab) {
        if(condition){
            logWarning(title, tab);
        }
    }

    public static void logWarning(String msg, Tabs tab) {
        telemetryUtil.put("[WARNING] " + msg, Robot.timeSinceInit, tab); }

    public static void logError(String msg, Tabs tab) {
        telemetryUtil.put("[ERROR] " + msg, Robot.timeSinceInit, tab); }

    public static void debugLog(String msg, Tabs tab) {
        telemetryUtil.put("[LOG] " + msg, Robot.timeSinceInit, tab); }











    public enum Tabs {

        DRIVER("SmartDashboard"),
        LIMELIGHT("Limelight"),
        DEBUG("Debug"),
        ROBOT("RobotInfo");

        String name;
        NetworkTable table;

        Tabs(String n) {
            this.name = n;
            this.table = NetworkTableInstance.getDefault().getTable(n);
        }
    }

    public static HashMap<String, StringPublisher> stringPubs = new HashMap<>();
    public static HashMap<String, IntegerPublisher> intPubs = new HashMap<>();
    public static HashMap<String, BooleanPublisher> boolPubs = new HashMap<>();
    public static HashMap<String, DoublePublisher> doublePubs = new HashMap<>();


    public static void put(String title, String s, Tabs t) {

        String path = t.name + "/" + title;
        StringPublisher p = stringPubs.get(path);
        if(p == null) { p = t.table.getStringTopic(title).publish();
            stringPubs.put(path, p); }
        p.set(s);
    }

    public static void put(String title, boolean s, Tabs t) {
        String path = t.name + "/" + title;
        BooleanPublisher p = boolPubs.get(path);
        if(p == null) { p = t.table.getBooleanTopic(title).publish();
            boolPubs.put(path, p); }
        p.set(s);
    }

    public static void put(String title, int s, Tabs t) {
        String path = t.name + "/" + title;
        IntegerPublisher p = intPubs.get(path);
        if(p == null) { p = t.table.getIntegerTopic(title).publish();
            intPubs.put(path, p); }
        p.set(s);
    }

    public static void put(String title, double s, Tabs t) {
        String path = t.name + "/" + title;
        DoublePublisher p = doublePubs.get(path);
        if(p == null) { p = t.table.getDoubleTopic(title).publish();
            doublePubs.put(path, p); }
        p.set(s);
    }

    public static void put(String title, double[][] s, Tabs t) {
        String path = t.name + "/" + title;
        StringPublisher p = stringPubs.get(path);
        if(p == null) { p = t.table.getStringTopic(title).publish();
            stringPubs.put(path, p); }
        String str = "";
        for(int i = 0; i < s.length; i++){
            str += "{";
            for(int j = 0; j < s[i].length; j++){
                str += String.valueOf(s[i][j]);
                str += ", ";
            }
            str += "},";
        }
        p.set(str);
    }








    public static final List<DashFuncChooser> funcChoosers = new ArrayList<>();
    public static final List<StringChooser> strChoosers = new ArrayList<>();


    public static void makeChooser(String name, Consumer<Consumer<Robot>> applyFunc, String defaultOpt, Class<?> loadClass) {

        DashFuncChooser d = new DashFuncChooser(name, applyFunc);
        SmartDashboard.putData(d.name, d.chooser);

        funcChoosers.add(d);


        d.chooser.setDefaultOption(defaultOpt, defaultOpt);
        if(defaultOpt != "nothing") { d.chooser.addOption("nothing", "nothing");}

        try{

            for(Field m : loadClass.getDeclaredFields()){

                if(m.getType().isAssignableFrom(Consumer.class)){

                    if(m.getAnnotation(Hidden.class) != null) {
                        continue; }

                    String optName = loadClass.getSimpleName() + "." + m.getName();
                    if(optName.equals(defaultOpt)) { continue; }

                    d.chooser.addOption(optName, optName);
                }
            }

        }
        catch (Exception e) {
            telemetryUtil.logError("Making chooser failed: " + e.toString(), Tabs.DRIVER);
        }
    }

    public static void makeChooser(String name, Consumer<String> setFunc, ArrayList<String> opts) {
        StringChooser s = new StringChooser(name, opts, setFunc);
        strChoosers.add(s);
        SmartDashboard.putData(s.name, s.chooser);
    }


    @SuppressWarnings("unchecked")
    public static void grabChoosers() {

        for(StringChooser d : strChoosers) {

            String sel = d.chooser.getSelected();

            if(sel != d.prev) {
                d.setFunc.accept(d.chooser.getSelected()); }

            d.prev = sel;
        }

        for(DashFuncChooser d : funcChoosers) {

            try{

                String s = d.chooser.getSelected();

                if(s.equals("nothing")) {
                    d.setTarget.accept( r -> { } );
                    continue;
                }


                int idx = s.indexOf(".");
                Class<?> c;

                try {
                    c = Class.forName("frc.robot.behaviours." + s.substring(0,idx));
                } catch(Exception e) {
                    c = Class.forName("frc.robot.behaviours.subsystem." + s.substring(0,idx));
                }

                Field f = c.getField(s.substring(idx+1));

                if(f.get(null) instanceof Consumer<?>) {
                    d.setTarget.accept((Consumer<Robot>)f.get(null)); }

            }
            catch (Exception e) {
                telemetryUtil.logError("getting choosers failed: " + e.toString(), Tabs.DRIVER);
            }
        }
    }

}
