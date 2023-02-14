package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.inputUtil;
import frc.robot.functions.turretUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;

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

    public static Consumer<Robot> systemTest = r -> {

        double x = inputUtil.deadzoneAxis(r.input.joystick.getX(), 0.20);
        double y = inputUtil.deadzoneAxis(r.input.joystick.getY(), 0.20);
        double z = inputUtil.deadzoneAxis(r.input.joystick.getZ(), 0.20);

        driveUtil.setPowerMechanum(r.drive, -x, -y, -z, 0.5);
       

        if (r.input.joystick.getRawButton(2)){

            turretUtil.runTurretMotor(r.turret, .2);

        }
        else if (r.input.joystick.getRawButton(3)){

            turretUtil.runTurretMotor(r.turret, -.2);

        }
        else{

            turretUtil.stopTurretMotor(r.turret);

        }

        if (r.input.joystick.getRawButton(1)){ r.brakes.brakeSolenoid.set(Value.kForward);} else { r.brakes.brakeSolenoid.set(Value.kReverse); }

    };


}
