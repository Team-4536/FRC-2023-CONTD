package frc.robot.constants;

public class VisionConstants {


    public static final double LIME_TOP_LIM_DEGREE = 24.85;
    public static final double LIME_BOT_LIM_DEGREE = -LIME_TOP_LIM_DEGREE;

    public static final double LIME_RIGHT_LIM_DEGREE = 29.8;
    public static final double LIME_LEFT_LIM_DEGREE = -LIME_RIGHT_LIM_DEGREE;

    public static final double LIMELIGHT_FOV_ASPECT = Math.sin(Math.toRadians(LIME_RIGHT_LIM_DEGREE))/Math.sin(Math.toRadians(LIME_TOP_LIM_DEGREE));





    public static final double TAG_SIZE_IN2 = 36;

    public static final double SUBSTATION_TAG_HEIGHT_INCHES = 27.375;
    public static final double GRID_TAG_HEIGHT_INCHES = 18.25;

    public static final double GRID_RRTAPE_HEIGHT_INCHES = 23.5;






    // measurements in inches of the distance of the camera on the xyz coordinate plane from the ground
    // DIRECTLY BELOW THE BACK LEFT corner of the robot
    public static final double CY_FROM_CENTER = 30.0;
    public static final double CX_FROM_CENTER = 8.0;
    public static final double CZ_FROM_CENTER = 8.25;

}
