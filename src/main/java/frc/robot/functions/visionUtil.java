package frc.robot.functions;

import frc.robot.constants.Constants.VisionInfo;
import frc.robot.functions.telemetryUtil.Tabs;

public class visionUtil {

    public static double distanceFrom(double tArea){

        double fovAreaIn = (100/tArea) * VisionInfo.TAG_SIZE_IN2;

        double fovHeight = Math.sqrt(fovAreaIn/VisionInfo.LIMELIGHT_FOV_ASPECT);
        double fovLength = fovHeight * VisionInfo.LIMELIGHT_FOV_ASPECT;

        double distanceFrom = (fovHeight/2)/Math.tan(Math.toRadians(VisionInfo.LIME_TOP_LIM_DEGREE));


        //who did this
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
