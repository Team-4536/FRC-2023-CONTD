package frc.robot.functions;

import frc.robot.Robot;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.VisionData;
import frc.robot.utils.V2d;

public class visionUtil {

    public static double distanceFrom(double tArea){

        double fovAreaIn = (100/tArea) * VisionConstants.TAG_SIZE_IN2;

        double fovHeight = Math.sqrt(fovAreaIn/VisionConstants.LIMELIGHT_FOV_ASPECT);
        // double fovLength = fovHeight * VisionConstants.LIMELIGHT_FOV_ASPECT;

        double distanceFrom = (fovHeight/2)/Math.tan(Math.toRadians(VisionConstants.LIME_TOP_LIM_DEGREE));

        return distanceFrom;

    }

    public static double horizontalOffset(double tArea, double tX){

        return (distanceFrom(tArea)*Math.tan(Math.toRadians(tX)));

    }

}
