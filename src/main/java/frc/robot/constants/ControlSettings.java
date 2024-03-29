package frc.robot.constants;

import frc.robot.utils.V2d;

public class ControlSettings {


    public static final double MAX_DRIVE_OUT = 1.0;
    public static final double DEFAULT_DRIVE_OUT = 0.1;

    public static final double TURNING_DEADZONE = 0.1;
    public static final double MOVEMENT_DEADZONE = 0.2;


    public static final double TURNING_SPEED = 30;

    public static final double TURNING_MULT = 0.2;

    public static final double LIFT_MULT = 0.1;


    public static final double TURRET_MULT = 1.0 / 8;
    public static final double RETRACTION_MULT = 1.4 / 1.4;

    public static final double DRIVE_PID_CLAMP = 0.6f;

    // RETRACT MOTOR ++++++++++++++++++++++++++++++++++++++++++++

    public static final double RETRACT_ENCODER_MINIMUM = 0;
    public static final double RETRACT_ENCODER_MAXIMUM = 11.2;

    public static final double RETRACT_PID_USER_MULTIPLIER = .47;

    public static final double RETRACT_MOTOR_MAX_OUTPUT = 7.0/7.0;

    public static final double RETRACT_PID_SETPOINT_COMPOUND_COEFFICIENT = 8;

    // LIFT MOTOR +++++++++++++++++++++++++++++++++++++++++++++++

    public static final double LIFT_ENCODER_MINIMUM = 0;
    public static final double LIFT_ENCODER_MAXIMUM = 14;

    public static final double LIFT_PID_USER_MULTIPLIER = .43;
    public static final double GYRO_PID_USER_MULTIPLIER = .3;

    public static final double LIFT_MOTOR_MAX_OUTPUT = 7.0/7.0;

    public static final double LIFT_PID_SETPOINT_COMPOUND_COEFFICIENT = 8;

    public static final double LIFT_GYRO_MINIMUM = -100000;
    public static final double LIFT_GYRO_MAXIMUM = 74;

    public static final double LIFT_GYRO_SETPOINT_COMPOUND_COEFFICIENT = 40;

    


    //TURRET MOTOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static final double TURRET_ENCODER_MINIMUM = -5.5;
    public static final double TURRET_ENCODER_MAXIMUM = 4;

    public static final double TURRET_PID_USER_MULTIPLIER = .14;

    public static final double TURRET_MOTOR_MAX_OUTPUT = 1.0/8.0;

    public static final double TURRET_PID_SETPOINT_COMPOUND_COEFFICIENT = 5.5;

    //ARM POSITIONING +++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static final V2d LOW_CONE = new V2d(1.8, 21.00);

    public static final V2d DOUBLE_SUB = new V2d(9.85, 36.83);

    public static final V2d HIGH_CONE = new V2d(7.78, 22);

    public static final V2d ARM_RESET = new V2d(0,-15);

}
