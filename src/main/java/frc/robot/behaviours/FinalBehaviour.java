package frc.robot.behaviours;

import java.util.function.Consumer;


import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.subsystems.IntakeData;
import frc.robot.subsystems.PneumaticData;
import frc.robot.constants.ControlSettings;
import frc.robot.functions.*;



public class FinalBehaviour {




    public static Consumer<Robot> periodic = r -> {

        //DRIVE =========================================================================================================

        double x = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), ControlSettings.CONTROLLER_STICK_DEADZONE);
        double y = inputUtil.deadzoneAxis(-r.input.controller.getLeftY(), ControlSettings.CONTROLLER_STICK_DEADZONE);
        double z = inputUtil.deadzoneAxis(r.input.controller.getRightX(), ControlSettings.CONTROLLER_STICK_DEADZONE);


        double driveScalar = inputUtil.mapInput(
            r.input.controller.getRightTriggerAxis(), 1, 0, ControlSettings.MAX_DRIVE_OUT, ControlSettings.DEFAULT_DRIVE_OUT);

        driveUtil.setPowerMechanum(r.drive, x * driveScalar, y * driveScalar, z * ControlSettings.TURNING_MULT, .8f);




        //PNEUMATICS ==================================================================================================

        if (r.input.controllerMech.getAButtonPressed()){ IntakeData.status = !IntakeData.status; }
        armUtil.runCondition(r.grabber, IntakeData.status);
        if (r.input.controller.getLeftBumperPressed()){ PneumaticData.status = !PneumaticData.status; }
        pneumaticUtil.runCondition(r.brakes, PneumaticData.status);



        //ARM ==================================================================================================

        telescopeUtil.liftScale(
            r.telescope,
            inputUtil.deadzoneAxis(
                -r.input.controllerMech.getRightY(),
                ControlSettings.CONTROLLER_STICK_DEADZONE),
            .8,
            .35);

        r.telescope.retractMotor.set(
            inputUtil.deadzoneAxis(
                r.input.controllerMech.getLeftY(),
                ControlSettings.CONTROLLER_STICK_DEADZONE
            ) * ControlSettings.RETRACTION_MULT
        );

        //r.IntakeData.grabberSolenoid.set(robAldrich.calculate())


        //TURRET ==================================================================================================

        double flymer = inputUtil.deadzoneAxis(
            r.input.controllerMech.getRightX(),
            ControlSettings.CONTROLLER_STICK_DEADZONE
        ) * ControlSettings.TURRET_MULT;

        turretUtil.run(r.turret, flymer);
    };









    @Hidden
    public static Consumer<Robot> teleOpInit = r -> {

        robotUtil.stopRobot(r);
        r.gyro.globGyroscope.reset();
        r.drive.pidController.target = r.gyro.globGyroscope.getAngle();
    };
}


// PID SHIT FOR LATER USE, KINDA BROKEN, WORTH TESTING

        /*
        boolean usePID = !r.input.controller.getRightBumper() && false;
        telemetryUtil.put("Turning mode is PID", usePID, Tabs.DEBUG);

        if(usePID) {

            t = inputUtil.deadzoneAxis(r.input.controller.getRightX(), 0.20);
            r.drive.pidController.target += Robot.dt * t * 60;
            telemetryUtil.put("TargetAngle", r.drive.pidController.target, Tabs.DEBUG);
            double PIDOut = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
            if(PIDOut > 0.2) { PIDOut = 0.2f; }
            if(PIDOut < -0.2) { PIDOut = -0.2f; }
            telemetryUtil.put("PIDOut", PIDOut, Tabs.DEBUG);
        }
        else {

            r.drive.pidController.integral = 0;
            r.drive.pidController.target = r.gyro.globGyroscope.getAngle();
        }
        */