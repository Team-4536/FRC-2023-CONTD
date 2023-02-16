package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.controllers.PIDController;
import frc.robot.functions.armUtil;
import frc.robot.functions.driveUtil;
import frc.robot.functions.inputUtil;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.turretUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.IntakeData;
import frc.robot.subsystems.PneumaticData;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class TestingBehaviour {


    // static PositionData p = new PositionData(null, null);
    // static Field2d f = new Field2d();

    public static Consumer<Robot> encoderPeriodic = r -> {

        // double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis()*2 - 1);


        // SmartDashboard.putData(f);
        // f.setRobotPose(p.prevPosition.x, p.prevPosition.y, new Rotation2d(r.gyro.globGyroscope.getAngle()));
    };

    public static Consumer<Robot> encoderInit = r -> {
        // p = new PositionData(r.gyro, r.drive);
    };



    public static Consumer<Robot> testLog = r -> {
        telemetryUtil.put("Testing str", "HI THERE", Tabs.DEBUG);
    };

    public static PIDController armPID = new PIDController(0.001, 0.0f, 0.0f);
    public static Consumer<Robot> systemTest = r -> {

        //DRIVE 
        double x = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), 0.20);
        double y = inputUtil.deadzoneAxis(-r.input.controller.getLeftY(), 0.20);
        double z = inputUtil.deadzoneAxis(r.input.controller.getRightX(), 0.20);

        if (DriveData.joystickDrive){

            x = inputUtil.deadzoneAxis(r.input.joystick.getX(), 0.20);
            y = inputUtil.deadzoneAxis(-r.input.joystick.getY(), 0.20);
            z = inputUtil.deadzoneAxis(r.input.joystick.getZ(), 0.20);

        }

        r.drive.pidController.target += Robot.dt * z * 60;

        double PIDOut = -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerMechanum(r.drive, x, y, PIDOut, 0.4);


        //PNEUMATICS
        if (r.input.controllerMech.getAButtonPressed()){ IntakeData.status = !IntakeData.status; }
        if (r.input.controllerMech.getBButtonPressed()){ PneumaticData.status = !PneumaticData.status; }
        pneumaticUtil.runCondition(r.brakes, PneumaticData.status);
        armUtil.runCondition(r.grabber, IntakeData.status);


        //ARM
        r.telescope.liftMotor.set(inputUtil.deadzoneAxis(r.input.controllerMech.getRightY(), .1)/2.3);
        r.telescope.retractMotor.set(inputUtil.deadzoneAxis(r.input.controllerMech.getLeftY(), .1)/1.4);

       
        //TURRET
        double flymer = inputUtil.deadzoneAxis(r.input.controllerMech.getRightTriggerAxis() - 
                                               r.input.controllerMech.getLeftTriggerAxis(), .1)/5;
        turretUtil.run(r.turret, flymer);
        
                        
        


    };


}
