package frc.robot.constants;

public class ControlSettings {


    public static final double MAX_DRIVE_OUT = 0.9;
    public static final double DEFAULT_DRIVE_OUT = 0.1;

    public static final double TURNING_DEADZONE = 0.1;
    public static final double MOVEMENT_DEADZONE = 0.2;


    public static final double TURNING_SPEED = 30;

    public static final double TURNING_MULT = 0.1;

    public static final double LIFT_MULT = 0.1;


    public static final double TURRET_MULT = 1.0 / 8;
    public static final double RETRACTION_MULT = 1.0 / 1.4;

    public static final double DRIVE_PID_CLAMP = 0.15f;

    // RETRACT MOTOR ++++++++++++++++++++++++++++++++++++++++++++

    public static final double RETRACT_ENCODER_MINIMUM = 0;
    public static final double RETRACT_ENCODER_MAXIMUM = 11;

    public static final double RETRACT_PID_USER_MULTIPLIER = .27;

    public static final double RETRACT_MOTOR_MAX_OUTPUT = 5.0/7.0;

    public static final double RETRACT_PID_SETPOINT_COMPOUND_COEFFICIENT = 2.6;

    // LIFT MOTOR +++++++++++++++++++++++++++++++++++++++++++++++

    public static final double LIFT_ENCODER_MINIMUM = 0;
    public static final double LIFT_ENCODER_MAXIMUM = 11;

    public static final double LIFT_PID_USER_MULTIPLIER = .1;

    public static final double LIFT_MOTOR_MAX_OUTPUT = 5.4/7.0;

    public static final double LIFT_PID_SETPOINT_COMPOUND_COEFFICIENT = 1.5;

}
