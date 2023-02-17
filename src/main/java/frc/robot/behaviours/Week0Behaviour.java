package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.functions.armUtil;
import frc.robot.functions.driveUtil;
import frc.robot.functions.inputUtil;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.turretUtil;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.IntakeData;
import frc.robot.subsystems.PneumaticData;

public abstract class Week0Behaviour {
    
    public static Consumer<Robot> periodic = r -> {

        //DRIVE 
        double x = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), 0.20);
        double y = inputUtil.deadzoneAxis(-r.input.controller.getLeftY(), 0.20) * .5;
        double z = inputUtil.deadzoneAxis(r.input.controller.getRightX(), 0.20) * .2;

        //  true is joystick, false is controller
        if (false){

            x = inputUtil.deadzoneAxis(r.input.joystick.getX(), 0.20);
            y = inputUtil.deadzoneAxis(-r.input.joystick.getY(), 0.20);
            z = inputUtil.deadzoneAxis(r.input.joystick.getZ(), 0.20);

        }

        r.drive.pidController.target += Robot.dt * z * 60;



        double driveScalar = inputUtil.mapInput(
            r.input.controller.getRightTriggerAxis(), 1, 0, Constants.ControlInfo.MAX_DRIVE_OUT, Constants.ControlInfo.DEFAULT_DRIVE_OUT);

        //double PIDOut = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        // if(PIDOut > 0.2) { PIDOut = 0.2f; }
        // if(PIDOut < -0.2) { PIDOut = -0.2f; }

        driveUtil.setPowerMechanum(r.drive, x * driveScalar, y * driveScalar, z, .8f);


        //PNEUMATICS
        if (r.input.controllerMech.getAButtonPressed()){ IntakeData.status = !IntakeData.status; }
        if (r.input.controllerMech.getBButtonPressed()){ PneumaticData.status = !PneumaticData.status; }
        pneumaticUtil.runCondition(r.brakes, PneumaticData.status);
        armUtil.runCondition(r.grabber, IntakeData.status);


        //ARM
        telescopeUtil.liftScale(r.telescope, inputUtil.deadzoneAxis(-r.input.controllerMech.getRightY(), .1), .8, .2);
        r.telescope.retractMotor.set(inputUtil.deadzoneAxis(r.input.controllerMech.getLeftY(), .1)/1.4);

       
        //TURRET
        double flymer = inputUtil.deadzoneAxis(r.input.controllerMech.getRightTriggerAxis() - 
                                               r.input.controllerMech.getLeftTriggerAxis(), .1)/8;
        turretUtil.run(r.turret, flymer);

                        
        


    };
}
