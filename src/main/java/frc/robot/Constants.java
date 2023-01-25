// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


public final class Constants {

    public static final long NANOS_PER_SECOND = 1000000000;


    public static final double POWER_SCALE_UPPER_BOUND = 1.0;
    public static final double POWER_SCALE_LOWER_BOUND = 0.2;

    public static final class ControlInfo {

        public static final double MOVEMENT_DEADZONE = 0.15;
        public static final double MAX_DRIVE_OUT = 1;
        public static final double DEFAULT_DRIVE_OUT = 0.4;


        public static final double TURNING_DEADZONE = 0.2;
        //                                          in degs/ sec
        public static final double MAX_TURNING_OUT = 100;
        public static final double DEFAULT_TURNING_OUT = 40;
    }



    public static final int DRIVE_FRONT_LEFT_PORT = 2;
    public static final int DRIVE_FRONT_RIGHT_PORT = 3;
    public static final int DRIVE_BACK_LEFT_PORT = 1;
    public static final int DRIVE_BACK_RIGHT_PORT = 0;

    public static final boolean DRIVE_FRONT_LEFT_FLIPPED = false;
    public static final boolean DRIVE_FRONT_RIGHT_FLIPPED = true;
    public static final boolean DRIVE_BACK_LEFT_FLIPPED = false;
    public static final boolean DRIVE_BACK_RIGHT_FLIPPED = true;


    public static final int ENCODER_TICKS_PER_REV = 360;
    public static final double ENCODER_PULSE_DISTANCE = 1/740.0;

    public static final double WHEEL_DIAMETER_INCHES = 6.0;
    public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;
    public static final double WHEEL_RADIUS = WHEEL_DIAMETER_INCHES;

    // FICKS
    public static final V2d FL_MOTOR_POS = new V2d(-1, 1);
    public static final V2d FR_MOTOR_POS = new V2d(1, 1);
    public static final V2d BL_MOTOR_POS = new V2d(-1, -1);
    public static final V2d BR_MOTOR_POS = new V2d(1, -1);



    public static final int CONTROLLER_PORT = 0;
    public static final int JOYSTICK_PORT = 1;

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


    }
}
