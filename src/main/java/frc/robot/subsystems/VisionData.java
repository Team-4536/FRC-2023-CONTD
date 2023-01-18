package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

      SmartDashboard.putBoolean("Target Found", getTargets());
      SmartDashboard.putNumber("Target X", getX());
      SmartDashboard.putNumber("Target Y", getY());
      SmartDashboard.putNumber("Target Area", getArea());

      this.pipeline5();

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

    public void pipeline1(){

      limelight.getEntry("pipeline").setNumber(1);

    }

    public void pipeline2(){

      limelight.getEntry("pipeline").setNumber(2);

    }

    public void pipeline3(){

      limelight.getEntry("pipeline").setNumber(3);

    }

    public void pipeline4(){

      limelight.getEntry("pipeline").setNumber(4);

    }

    public void pipeline5(){

      limelight.getEntry("pipeline").setNumber(5);

    }

    public void pipeline6(){

      limelight.getEntry("pipeline").setNumber(6);
      
    }

    public void pipeline7(){

      limelight.getEntry("pipeline").setNumber(7);
      
    }

    public void pipeline8(){

      limelight.getEntry("pipeline").setNumber(8);
      
    }
    
}