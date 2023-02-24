package frc.robot.functions;

import frc.robot.constants.ControlSettings;
import frc.robot.subsystems.TelescopeData;

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
