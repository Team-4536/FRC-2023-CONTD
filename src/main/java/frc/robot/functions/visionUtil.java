package frc.robot.functions;

import frc.robot.Constants.VisionInfo;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.GyroData;
import frc.robot.subsystems.VisionData;

public class visionUtil {

    public static void alignHorizontal(DriveData drive, VisionData vision, GyroData gyro){

        driveUtil.setPowerTank(drive, .5, 0, 1);

    }

    public static double distanceFrom(double tArea){

        double fovAreaIn = (100/tArea) * VisionInfo.TAG_SIZE_IN2;

        double fovHeight = Math.sqrt(fovAreaIn/VisionInfo.LIMELIGHT_FOV_ASPECT);
        double fovLength = fovHeight * VisionInfo.LIMELIGHT_FOV_ASPECT;

        double distanceFrom = (fovHeight/2)/Math.tan(Math.toRadians(VisionInfo.LIME_TOP_LIM_DEGREE));

        boolean lol = false;

        if (lol){

            telemetryUtil.put("fov AREA", fovAreaIn, Tabs.LIMELIGHT);
            telemetryUtil.put("fov HEIGHT", fovHeight, Tabs.LIMELIGHT);
            telemetryUtil.put("fov LENGTH", fovLength, Tabs.LIMELIGHT);
            telemetryUtil.put("distance FROM", distanceFrom, Tabs.LIMELIGHT);

        }

        return distanceFrom;

    }

    public static double horizontalOffset(double tArea, double tX){

        return (distanceFrom(tArea)*Math.tan(Math.toRadians(tX)));

    }
    
}
