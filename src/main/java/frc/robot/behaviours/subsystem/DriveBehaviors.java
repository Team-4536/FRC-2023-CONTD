package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.driveUtil;
import frc.robot.functions.gyroUtil;
import frc.robot.utils.inputUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.utils.V2d;


public class DriveBehaviors {



    public static final Consumer<Robot> driveMech  = r -> {

        double x = inputUtil.deadzoneStick(r.input.driveController.getLeftX());
        double y = inputUtil.deadzoneStick(-r.input.driveController.getLeftY());
        double z = inputUtil.deadzoneAxis(r.input.driveController.getRightX(), ControlSettings.TURNING_DEADZONE);

        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        driveUtil.setPowerMechanum(r.drive, x * driveScalar, y * driveScalar, z * ControlSettings.TURNING_MULT, 0.8);
    };






    public static final Consumer<Robot> absoluteDrive = r -> {

        double x = inputUtil.deadzoneStick(r.input.driveController.getLeftX());
        double y = inputUtil.deadzoneStick(-r.input.driveController.getLeftY());
        double z = inputUtil.deadzoneStick(r.input.driveController.getRightX());

        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        V2d flymer = new V2d(x * driveScalar, y * driveScalar);
        flymer = flymer.rotateDegrees(r.gyro.getYaw());

        driveUtil.pid.target += z * ControlSettings.TURNING_SPEED * Robot.dt * 4;

        driveUtil.pid.target = gyroUtil.wrapAngle(driveUtil.pid.target);

        telemetryUtil.put("angle traget", driveUtil.pid.target, Tabs.DEBUG);

        double drivePIDOut = driveUtil.pid.tick(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle()), Robot.dt, true) + r.input.driveController.getRightY() * .18;

        double pwr = drivePIDOut;
        if(pwr > ControlSettings.DRIVE_PID_CLAMP) { pwr = ControlSettings.DRIVE_PID_CLAMP; }
        if(pwr < -ControlSettings.DRIVE_PID_CLAMP) { pwr = -ControlSettings.DRIVE_PID_CLAMP; }
        
        driveUtil.setPowerMechanum(r.drive, flymer.x, flymer.y, pwr, 0.8);
        telemetryUtil.put("Drive PID target", driveUtil.pid.target, Tabs.ROBOT);

        if(r.input.driveController.getPOV() != -1){
            driveUtil.pid.reset();
            driveUtil.pid.target = r.input.driveController.getPOV();
        }

        if(r.input.driveController.getRightStickButtonPressed()){
            driveUtil.pid.reset();
            driveUtil.pid.target = r.gyro.globGyroscope.getAngle()+180;
        }

        if (r.input.driveController.getYButtonPressed()){

            r.gyro.globGyroscope.reset();
            driveUtil.pid.reset();
            driveUtil.pid.target = 0;

        }

    };




    public static final Consumer<Robot> emmettDrive = r -> {

        Timer emmettSackett = new Timer();

        double x = inputUtil.deadzoneStick(r.input.driveController.getLeftX());
        double y = inputUtil.deadzoneStick(-r.input.driveController.getLeftY());
        double z = inputUtil.deadzoneStick(r.input.driveController.getRightX());

        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        V2d flymer = new V2d(x * driveScalar, y * driveScalar);
        flymer = flymer.rotateDegrees(r.gyro.getYaw());

        telemetryUtil.put("angle traget", driveUtil.pid.target, Tabs.DEBUG);

        double drivePIDOut = driveUtil.pid.tick(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle()), Robot.dt, true) + r.input.driveController.getRightY() * .18;

        double pwr = drivePIDOut;

        if (!(z == 0)){
            pwr = inputUtil.mapInput(z, 1.0, -1.0, 0.3, -0.3);
            driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
            emmettSackett.reset();
            emmettSackett.start();
        }
        if (emmettSackett.get() <= .25){
            driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
        }
        if (emmettSackett.get() >= .25){
            emmettSackett.reset();
            emmettSacket.stop();
        }
        

        telemetryUtil.put("balls22", emmettSackett.get(), Tabs.DEBUG);

        driveUtil.pid.target = gyroUtil.wrapAngle(driveUtil.pid.target);

        if(pwr > ControlSettings.DRIVE_PID_CLAMP) { pwr = ControlSettings.DRIVE_PID_CLAMP; }
        if(pwr < -ControlSettings.DRIVE_PID_CLAMP) { pwr = -ControlSettings.DRIVE_PID_CLAMP; }

        if(r.input.driveController.getPOV() != -1){

            driveUtil.pid.reset();
            driveUtil.pid.target = r.input.driveController.getPOV();

        }

        if(r.input.driveController.getRightStickButtonPressed()){

            driveUtil.pid.reset();
            driveUtil.pid.target = r.gyro.globGyroscope.getAngle()+180;

        }

        if (r.input.driveController.getYButtonPressed()){

            r.gyro.globGyroscope.reset();
            driveUtil.pid.reset();
            driveUtil.pid.target = 0;

        }

        driveUtil.setPowerMechanum(r.drive, flymer.x, flymer.y, pwr, 0.8);
        telemetryUtil.put("Drive PID target", driveUtil.pid.target, Tabs.ROBOT);

    };




    
}
