// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

public final class Constants {

    public static final long NANOS_PER_SECOND = 1000000000;



    public static final class VisionInfo{

        public static final double TAG_SIZE_IN2 = 36;

        public static final double LIME_TOP_LIM_DEGREE = 24.85;
        public static final double LIME_BOT_LIM_DEGREE = -LIME_TOP_LIM_DEGREE;

        public static final double LIME_RIGHT_LIM_DEGREE = 29.8;
        public static final double LIME_LEFT_LIM_DEGREE = -LIME_RIGHT_LIM_DEGREE;

        public static final double LIMELIGHT_FOV_ASPECT = Math.sin(Math.toRadians(27))/Math.sin(Math.toRadians(20.5));

        public static final double GRID_TAG_HEIGHT_INCHES = 15.25;
        public static final double SUBSTATION_TAG_HEIGHT_INCHES = 24.375;

        public static final double GRID_RRTAPE_HEIGHT_INCHES = 23.5;

        public static final double APRIL_TAG_COMMUNITY_HIGHT = 18.25;

        //public static final int CAMMERA_X_OFFSET_FROM_CENTER = 7;
        //public static final int CAMMERA_Y_OFFSET_FROM_GROUND = 16;
        //public static final int CAMMERA_Z_OFFSET_FROM_FRONT_ROBOT = 23;

        public static final double CY_FROM_CENTER = 7.75;
        public static final double CX_FROM_CENTER = 5.333333333333;
        public static final double CZ_FRIM_CENTER = 16;
    }

}
