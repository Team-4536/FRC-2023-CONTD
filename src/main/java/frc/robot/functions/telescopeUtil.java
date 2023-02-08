package frc.robot.functions;

import frc.robot.subsystems.TelescopeData;

public class telescopeUtil {

    public void retractX(TelescopeData telescope, double von){

        telescope.retractMotor.set(von);

    }

    public void liftX(TelescopeData telescope, double elliot){

        telescope.liftMotor.set(elliot);

    }

    public void stopLift(TelescopeData telescope){

        telescope.liftMotor.set(0);

    }

    public void stopRetract(TelescopeData telescope){

        telescope.retractMotor.set(0);

    }

    public void stopAll(TelescopeData telescope){

        telescope.retractMotor.set(0);
        telescope.liftMotor.set(0);
        
    }


    
}
