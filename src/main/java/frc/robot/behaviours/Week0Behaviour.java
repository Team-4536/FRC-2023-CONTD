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
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.IntakeData;
import frc.robot.subsystems.PneumaticData;
import frc.robot.functions.telemetryUtil;

public abstract class Week0Behaviour {

    public static Consumer<Robot> periodic = r -> {

        //DRIVE
        double x = inputUtil.deadzoneAxis(r.input.controller.getLeftX(), 0.20);
        double y = inputUtil.deadzoneAxis(-r.input.controller.getLeftY(), 0.20);
        double z = inputUtil.deadzoneAxis(r.input.controller.getRightX(), 0.20) * .3;

        double t = z;

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


        double driveScalar = inputUtil.mapInput(
        r.input.controller.getRightTriggerAxis(), 1, 0, Constants.ControlInfo.MAX_DRIVE_OUT, Constants.ControlInfo.DEFAULT_DRIVE_OUT);



        driveUtil.setPowerMechanum(r.drive, x * driveScalar, y * driveScalar, t, .8f);


        //PNEUMATICS
        if (r.input.controllerMech.getAButtonPressed()){ IntakeData.status = !IntakeData.status; }
        if (r.input.controller.getLeftBumperPressed()){ PneumaticData.status = !PneumaticData.status; }
        pneumaticUtil.runCondition(r.brakes, PneumaticData.status);
        armUtil.runCondition(r.grabber, IntakeData.status);


        //ARM
        telescopeUtil.liftScale(r.telescope, inputUtil.deadzoneAxis(-r.input.controllerMech.getRightY(), .1), .8, .35);
        r.telescope.retractMotor.set(inputUtil.deadzoneAxis(r.input.controllerMech.getLeftY(), .1)/1.4);

        //TURRET
        double flymer = inputUtil.deadzoneAxis(r.input.controllerMech.getRightX(), .2)/8;
        turretUtil.run(r.turret, flymer);

    };
}
