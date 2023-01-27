package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.visionUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class VisionData {

    public NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    public NetworkTableEntry tv = limelight.getEntry("tv");
    public NetworkTableEntry tx = limelight.getEntry("tx");
    public NetworkTableEntry ty = limelight.getEntry("ty");
    public NetworkTableEntry ta = limelight.getEntry("ta");

    private boolean targetFound = (!(tv.getDouble(0.0) == 0));
    private double targetX = tx.getDouble(0.0);
    private double targetY = ty.getDouble(0.0);
    private double targetArea = ta.getDouble(0.0);

    public void sendTelemetry(){

      telemetryUtil.put("Target Found", getTargets(), Tabs.LIMELIGHT);
      telemetryUtil.put("Target X", getX(), Tabs.LIMELIGHT);
      telemetryUtil.put("Target Y", getY(), Tabs.LIMELIGHT);
      telemetryUtil.put("Target Area", getArea(), Tabs.LIMELIGHT);

      telemetryUtil.put("Distance From Target", visionUtil.distanceFrom(getArea()), Tabs.LIMELIGHT);
      telemetryUtil.put("Horizontal Offset", visionUtil.horizontalOffset(getArea(), getX()), Tabs.LIMELIGHT);

    }

    public boolean getTargets(){

      targetFound = (!(tv.getDouble(0.0) == 0));

      return targetFound;
    }

    public double getX(){

      targetX = tx.getDouble(0.0);
      return targetX;

    }

    public double getY(){

      targetY = ty.getDouble(0.0);
      return targetY;

    }

    public double getArea(){

      targetArea = ta.getDouble(0.0);
      return targetArea;

    }

    public void pipelineRR(){

      limelight.getEntry("pipeline").setNumber(0);

    }

    public void pipelineTag(int t){

      limelight.getEntry("pipeline").setNumber(t);

    }
}