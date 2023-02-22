package frc.robot.functions;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;

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

    public static void limitSwitchCheck(TelescopeData tData, double speed, double pScale, double nScale){

        double pPow = speed * pScale;
        double nPow = speed * nScale;

        if (tData.upBound.get() && speed > 0)
        {tData.liftMotor.set(0);} 
        else if (tData.lowBound.get() && speed < 0)
        {tData.liftMotor.set(0);} 
        else {
            
            if (speed > 0){
                tData.liftMotor.set(pPow);
            }
            else if (speed < 0){
                tData.liftMotor.set(nPow);
            } 
            else {
                tData.liftMotor.set(0);
            }
            
        }

    }


}
