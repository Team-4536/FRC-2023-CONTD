package frc.robot.behaviours;

import java.util.function.Consumer;


import frc.robot.Robot;
import frc.robot.behaviours.annotations.Hidden;
import frc.robot.functions.driveUtil;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.functions.*;



public class FinalBehaviour {


    public static PIDController liftPID = new PIDController(0.4, 0.1, 0);

    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        robotUtil.stopRobot(r);
        r.gyro.globGyroscope.reset();
        driveUtil.pid.target = r.gyro.globGyroscope.getAngle();
        r.telescope.liftEncoder.setPosition(100);
        r.telescope.retractEncoder.setPosition(100);

        liftPID.target = r.telescope.liftVal();
    };



    public static Consumer<Robot> periodic = r -> {

        //DRIVE =========================================================================================================

        double x = inputUtil.deadzoneAxis(r.input.driveController.getLeftX(), ControlSettings.CONTROLLER_STICK_DEADZONE);
        double y = inputUtil.deadzoneAxis(-r.input.driveController.getLeftY(), ControlSettings.CONTROLLER_STICK_DEADZONE);
        double z = inputUtil.deadzoneAxis(r.input.driveController.getRightX(), ControlSettings.CONTROLLER_STICK_DEADZONE);


        double driveScalar = inputUtil.mapInput(
            r.input.driveController.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        driveUtil.pid.target += z * ControlSettings.TURNING_SPEED * Robot.dt;
        driveUtil.setPowerMechPID(r, x * driveScalar, y * driveScalar, 0.8);
        telemetryUtil.put("Drive PID target", driveUtil.pid.target, Tabs.ROBOT);




        //PNEUMATICS ==================================================================================================

        if (r.input.armController.getAButtonPressed()){
            pneumaticUtil.toggleSolenoid(r.grabber.grabberSolenoid); }

        if (r.input.driveController.getLeftBumperPressed()){
            pneumaticUtil.toggleSolenoid(r.brakes.brakeSolenoid); }







        //ARM ==================================================================================================

        liftPID.target += inputUtil.deadzoneAxis(
            r.input.armController.getRightY(),
            ControlSettings.CONTROLLER_STICK_DEADZONE) * Robot.dt;

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);
        r.telescope.liftMotor.set(PIDOut * (r.input.armController.getRightBumper()?1:0));
        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);



        r.telescope.retractMotor.set(
            inputUtil.deadzoneAxis(
                r.input.armController.getLeftY(),
                ControlSettings.CONTROLLER_STICK_DEADZONE
            ) * ControlSettings.RETRACTION_MULT
        );


        double flymer = inputUtil.deadzoneAxis(
            r.input.armController.getRightX(),
            ControlSettings.CONTROLLER_STICK_DEADZONE
        ) * ControlSettings.TURRET_MULT;

        turretUtil.run(r.turret, flymer);
    };
}