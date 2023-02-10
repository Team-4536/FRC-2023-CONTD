package frc.robot.functions;

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


    
}
