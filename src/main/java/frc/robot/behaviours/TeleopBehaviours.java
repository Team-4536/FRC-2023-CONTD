package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.driveUtil;
import frc.robot.functions.inputUtil;
import frc.robot.functions.pneumaticUtil;
import frc.robot.functions.robotUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.turretUtil;
import frc.robot.functions.telemetryUtil.Tabs;

public class TeleopBehaviours {


    // this burns my eyes
    public static PIDController liftPID = new PIDController(0.4, 0.01, 0);
    public static Consumer<Robot> teleOpInit = r -> {

        robotUtil.stopRobot(r);

        r.telescope.liftEncoder.setPosition(100);
        r.telescope.retractEncoder.setPosition(100);

        r.gyro.globGyroscope.reset();
        driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
        liftPID.target = r.telescope.liftVal();

        telemetryUtil.debugLog("LOG", Tabs.DEBUG);
    };




    public static final Consumer<Robot> driveMechPID = r -> {

        double x = inputUtil.deadzoneStick(r.input.driveController.getLeftX());
        double y = inputUtil.deadzoneStick(-r.input.driveController.getLeftY());
        double z = inputUtil.deadzoneAxis(r.input.driveController.getRightX(), ControlSettings.TURNING_DEADZONE);


        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        driveUtil.pid.target += z * ControlSettings.TURNING_SPEED * Robot.dt;
        driveUtil.setPowerMechPID(r, x * driveScalar, y * driveScalar, 0.8);
        telemetryUtil.put("Drive PID target", driveUtil.pid.target, Tabs.ROBOT);
    };

    public static final Consumer<Robot> driveMech  = r -> {

        double x = inputUtil.deadzoneStick(r.input.driveController.getLeftX());
        double y = inputUtil.deadzoneStick(-r.input.driveController.getLeftY());
        double z = inputUtil.deadzoneAxis(r.input.driveController.getRightX(), ControlSettings.TURNING_DEADZONE);

        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        driveUtil.setPowerMechanum(r.drive, x * driveScalar, y * driveScalar, z * ControlSettings.TURNING_MULT, 0.8);
    };





    public static final Consumer<Robot> controlPnuematics = r -> {

        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }

        if (r.input.driveController.getLeftBumperPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }
    };






    public static final Consumer<Robot> controlRetractionUnbounded = r -> {
        r.telescope.retractMotor.set(
            inputUtil.deadzoneAxis(
                r.input.armController.getLeftY(),
                ControlSettings.MOVEMENT_DEADZONE
            ) * ControlSettings.RETRACTION_MULT
        );
    };





    public static final Consumer<Robot> controlTurretBounded = r -> {

        double flymer = inputUtil.deadzoneStick(r.input.armController.getRightX())
            * ControlSettings.TURRET_MULT;

        turretUtil.run(r.turret, flymer);
    };





    public static final Consumer<Robot> controlLiftUnboundedPID = r -> {

        liftPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt;

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);
        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);

        r.telescope.liftMotor.set(PIDOut);
    };


    public static final Consumer<Robot> controlLiftUnbounded = r -> {

        double pwr = inputUtil.deadzoneStick(r.input.armController.getRightY())
            * ControlSettings.LIFT_MULT;

        r.telescope.liftMotor.set(pwr);
    };

}
