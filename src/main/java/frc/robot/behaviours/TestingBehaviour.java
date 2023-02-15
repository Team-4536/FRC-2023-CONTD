package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.controllers.PIDController;
import frc.robot.functions.armUtil;
import frc.robot.functions.driveUtil;
import frc.robot.functions.inputUtil;
import frc.robot.functions.turretUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.telemetryUtil.Tabs;
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

        double x = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), 0.20);
        double y = inputUtil.deadzoneAxis(-r.input.controller.getLeftY(), 0.20);
        double z = inputUtil.deadzoneAxis(r.input.controller.getRightX(), 0.20);
        r.drive.pidController.target += Robot.dt * z * 60;

        double PIDOut = -r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerMechanum(r.drive, x, y, PIDOut, 0.4);

        if (r.input.controller.getAButton()) { r.brakes.brakeSolenoid.set(Value.kForward); }
        else { r.brakes.brakeSolenoid.set(Value.kReverse);}
        if (r.input.controller.getBButton()) { r.grabber.armSolenoid.set(Value.kForward); }
        else { r.grabber.armSolenoid.set(Value.kReverse);}

        //if (r.input.controller.getXButton()){ r.telescope.liftMotor.set(.2); }
        //else { r.telescope.liftMotor.set(0); }
        r.telescope.liftMotor.set(r.input.controller.getRightTriggerAxis() - r.input.controller.getLeftTriggerAxis());
        
        //if (r.input.controller.getYButton()){ r.telescope.retractMotor.set(.5); }
        //else {r.telescope.retractMotor.set(0); }
        r.telescope.retractMotor.set((r.input.controller.getStartButtonPressed()?1:0) - (r.input.controller.getBackButtonPressed()?1:0));

       

        if (r.input.joystick.getRawButton(2)){

            turretUtil.runTurretMotor(r.turret, .2);

        }
        else if (r.input.joystick.getRawButton(3)){

            turretUtil.runTurretMotor(r.turret, -.2);

        }
        else{

            turretUtil.stopTurretMotor(r.turret);

        }


    };


}
