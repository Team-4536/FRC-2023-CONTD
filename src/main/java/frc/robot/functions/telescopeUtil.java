package frc.robot.functions;


import frc.robot.behaviours.subsystem.LiftBehaviors;
import frc.robot.behaviours.subsystem.RetractionBehaviors;

import frc.robot.Robot;

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
    // in inches
    public static double armDistanceByEncoder(double encoderVal){

        //This is in inches
         //3.25in every rotation 
        return (encoderVal * 3.25) + 44;
    }
    public static double armDistanceByEncoderCm(double encoderVal){
        //2.54cm for every inch so in*2.54
        return armDistanceByEncoder(encoderVal) * 2.54;
    }

    public static void setArmPositionPID(V2d position){

        RetractionBehaviors.retractPID.target = position.x;
        LiftBehaviors.liftPID.target = position.y;
    }

    public static double getMaxArmEncoder() {

        double r = Robot.instance.telescope.liftEncoder.getPosition() * ControlSettings.LIFT_TO_DISTANCE_CONVERSION;
        double theta = Math.atan(r / ControlSettings.LEAD_SCREW_DIST);

        // 22in is joint to frame edge, 
        // max entension is 48 in
        double maxExtensionDist = 70 * Math.cos(theta);
        double encoderVal = (maxExtensionDist - 44) / 3.25;
        return encoderVal;


    }
    

    /*public static double getArmAngle(double liftVal){
        //max = 72.5 degreese

    }*/
    /*limit thin
     * 
      double pPow = PIDOut * pScale;
        double nPow = PIDOut * nScale;

        if (r.telescope.upBound.get() && PIDOut > 0)
        {r.telescope.liftMotor.set(0);} 
        else if (r.telescope.lowBound.get() && PIDOut < 0)
        {r.telescope.liftMotor.set(0);} 
        else {
            
            if (speed > 0){
                r.telescope.liftMotor.set(pPow);
            }
            else if (speed < 0){
                r.telescope.liftMotor.set(nPow);
            } 
            else {
                r.telescope.liftMotor.set(0);
            }
            
        }
     * 
     * 
     * 
     */

}
