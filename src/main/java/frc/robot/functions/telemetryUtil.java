package frc.robot.functions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

import com.sun.source.tree.Tree.Kind;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.IntegerPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.behaviours.Hidden;






class DashFuncChooser {
    SendableChooser<String> chooser;
    String name;
    Consumer<Consumer<Robot>> setTarget;

    public DashFuncChooser() { };
    public DashFuncChooser(SendableChooser<String> c, String n, Consumer<Consumer<Robot>> t) {
        this.chooser = c;
        this.name = n;
        this.setTarget = t; };
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
        DEBUG("Debug");

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










    public static final List<DashFuncChooser> funcChoosers = List.of(
        new DashFuncChooser(new SendableChooser<String>(), "Auto Init", x -> { Robot.AUTO_INIT_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Auto Periodic", x -> { Robot.AUTO_PER_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Test Init", x -> { Robot.TEST_INIT_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Test Periodic", x -> { Robot.TEST_PER_FUNC = x; })
    );

    public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
            .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
            .filter(line -> line.endsWith(".class"))
            .map(line -> getClass(line, packageName))
            .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public static void initChoosers() {


        for(DashFuncChooser d : funcChoosers) {

            SmartDashboard.putData(d.name, d.chooser);
            d.chooser.setDefaultOption("nothing", "");

            Set<Class<?>> classes = findAllClassesUsingClassLoader("frc.robot.behaviours");
            for(Class<?> c : classes) {
                for(Field m : c.getDeclaredFields()){

                    if(m.getType().isAssignableFrom(Consumer.class)){

                        if(m.getAnnotation(Hidden.class) == null){

                            String name = c.getSimpleName() + "." + m.getName();
                            d.chooser.addOption(name, name);
                        }
                    }
                }
            }
        }


    }

    @SuppressWarnings("unchecked")
    public static void grabChoosers() {

        for(DashFuncChooser d : funcChoosers) {

            try{

                String s = d.chooser.getSelected();

                if(s.equals("")) {
                    d.setTarget.accept( r -> { } );
                    continue;
                }

                int idx = s.indexOf(".");
                Class<?> c = Class.forName("frc.robot.behaviours." + s.substring(0,idx));
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
