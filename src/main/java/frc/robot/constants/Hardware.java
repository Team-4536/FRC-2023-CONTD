package frc.robot.constants;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.V2d;

public final class Hardware {



    // *INPUT PORTS* =============================================================================================
    // #region

    public static final int CONTROLLER_PORT = 0;
    public static final int CONTROLLER_MECH_PORT = 2;

    public static final int JOYSTICK_PORT = 1;

    // #endregion
    // *INPUT PORTS* =============================================================================================







    // *DRIVE CONFIG* =============================================================================================
    // #region

    public static final MotorType DRIVE_MOTOR_TYPE = MotorType.kBrushless;

    public static final int DRIVE_FRONT_LEFT_PORT = 4;
    public static final int DRIVE_FRONT_RIGHT_PORT = 1;
    public static final int DRIVE_BACK_LEFT_PORT = 3;
    public static final int DRIVE_BACK_RIGHT_PORT = 2;

    public static final boolean DRIVE_FRONT_LEFT_FLIPPED = false;
    public static final boolean DRIVE_FRONT_RIGHT_FLIPPED = true;
    public static final boolean DRIVE_BACK_LEFT_FLIPPED = false;
    public static final boolean DRIVE_BACK_RIGHT_FLIPPED = true;

    // #endregion
    // *DRIVE CONFIG* =============================================================================================







    // *PENUMATICS SYSTEMS* =======================================================================================
    // #region

    // Penumatics controller
    public static final int PCM_ID_PORT = 0;

    public static final int BRAKE_SOLENOID_FORWARD_PORT = 4;
    public static final int BRAKE_SOLENOID_REVERSE_PORT = 6;

    public static final int GRABBER_SOLENOID_FORWARD_PORT = 7;
    public static final int GRABBER_SOLENOID_REVERSE_PORT = 5;

    // #endregion
    // *PENUMATICS SYSTEMS* =======================================================================================







    // *SWITCHES* ==================================================================================================
    // #region

    // lift motors
    public static final int UP_BOUND_ID = 6;
    public static final int LOW_BOUND_ID = 4;

    // turret
    public static final int CLOCKWISE_BOUND_ID = 5;
    public static final int COUNTERCLOCKWISE_BOUND_ID = 7;

    // #region
    // *SWITCHES* ==================================================================================================




    // *ARM* =======================================================================================================
    // #region

    public static final MotorType RETRACT_MOTOR_TYPE = MotorType.kBrushed;
    public static final MotorType LIFT_MOTOR_TYPE = MotorType.kBrushed;

    public static final MotorType TURRET_MOTOR_TYPE = MotorType.kBrushless;


    public static final int ARM_LIFT_ID = 7;
    public static final int ARM_RETRACT_ID = 6;

    public static final int TURRET_ID = 5;

    // #endregion
    // *ARM* =======================================================================================================








    // *ROBOT SPECS* ===============================================================================================

    public static final double INCH_TO_METER = 1/0.0254;

    public static final double WHEEL_DIAMETER_INCHES = 8;
    public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI * WHEEL_DIAMETER_INCHES;
    public static final double WHEEL_RADIUS = WHEEL_DIAMETER_INCHES / 2.0;

    public static final int ENCODER_TICKS_PER_REV = 360;
    public static final double ENCODER_CONVERSION = 1/18.02686;

    // IN INCHES, origin is back right
    public static final double WHEEL_X_FROM_EDGE = 3.25;
    public static final double WHEEL_Y_FROM_EDGE = 6;

    public static final double ROBOT_X_SIZE_IN = 27;
    public static final double ROBOT_Y_SIZE_IN = 32.25;

    // METERS LOL STANDARDS ARE FOR BItCHES
    public static final V2d FL_MOTOR_POS = new V2d(ROBOT_X_SIZE_IN - WHEEL_X_FROM_EDGE, ROBOT_Y_SIZE_IN - WHEEL_Y_FROM_EDGE).multiply(INCH_TO_METER);
    public static final V2d FR_MOTOR_POS = new V2d(WHEEL_X_FROM_EDGE,                   ROBOT_Y_SIZE_IN - WHEEL_Y_FROM_EDGE).multiply(INCH_TO_METER);
    public static final V2d BL_MOTOR_POS = new V2d(ROBOT_X_SIZE_IN - WHEEL_X_FROM_EDGE, WHEEL_Y_FROM_EDGE).multiply(INCH_TO_METER);
    public static final V2d BR_MOTOR_POS = new V2d(WHEEL_X_FROM_EDGE,                   WHEEL_Y_FROM_EDGE).multiply(INCH_TO_METER);

    // ALSO INCHES :)
    public static final V2d ROBOT_CENTER = new V2d(ROBOT_X_SIZE_IN, ROBOT_Y_SIZE_IN).divide(2);
    public static final V2d ROBOT_COR = ROBOT_CENTER;

    // *ROBOT SPECS* ===============================================================================================




}
