package frc.robot.functions;

import frc.robot.behaviours.subsystem.LiftBehaviors;
import frc.robot.behaviours.subsystem.RetractionBehaviors;
import frc.robot.constants.ControlSettings;
import frc.robot.subsystems.TelescopeData;
import frc.robot.utils.V2d;

public class telescopeUtil {

    public static void retractX(TelescopeData telescope, double von){

        telescope.retractMotor.set(von);

    }

    public static void liftX(TelescopeData telescope, double elliot){

        telescope.liftMotor.set(elliot);

    }

    public static void stopLift(TelescopeData telescope){

        telescope.liftMotor.set(0);

    }

    public static void stopRetract(TelescopeData telescope){

        telescope.retractMotor.set(0);

    }

    public static void stopAll(TelescopeData telescope){

        telescope.retractMotor.set(0);
        telescope.liftMotor.set(0);

    }

    public static void liftScale(TelescopeData telescope, double speed, double pScale, double nScale){

        if (speed < 0){
            telescope.liftMotor.set(speed * nScale);
        } else if (speed > 0){
            telescope.liftMotor.set(speed * pScale);
        } else {
            stopLift(telescope);
        }

    }

    public static void softLimitRetract(TelescopeData telescope, double speed){

        if (telescope.retractVal() <= ControlSettings.RETRACT_ENCODER_MINIMUM && speed > 0){
            telescope.retractMotor.set(0);
        } else if (telescope.retractVal() >= ControlSettings.RETRACT_ENCODER_MAXIMUM && speed < 0){
            telescope.retractMotor.set(0);
        } else {
            telescope.retractMotor.set(speed);
        }

    }


    public static void softHardLimitLift(TelescopeData telescope, double speed){

        if ((telescope.liftVal() <= ControlSettings.LIFT_ENCODER_MINIMUM || telescope.upBound.get()) && speed > 0){
            telescope.liftMotor.set(0);
        } else if ((telescope.liftVal() >= ControlSettings.LIFT_ENCODER_MAXIMUM || !telescope.lowBound.get()) && speed < 0){
            telescope.liftMotor.set(0);
        } else {
            telescope.liftMotor.set(speed);
        }

    }


    //returns the distnace from the joint of the arm at the table to the base of the grabbing mechanism (gray plastic piece)
    public static double armDistanceByEncoder(double encoderVal){

        return (encoderVal * 3.25) + 35;

    }

    public static void setArmPositionPID(V2d position){

        RetractionBehaviors.retractPID.target = position.x;
        LiftBehaviors.gyroPID.target = position.y;

    }

}
