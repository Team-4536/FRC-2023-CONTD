package frc.robot.behaviours.subsystem;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telescopeUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.utils.inputUtil;


public class LiftBehaviors {

    public static PIDController liftPID = new PIDController(0.4, 0.01, 0);
    public static PIDController gyroPID = new PIDController(0.08, 0.0005, -.003);

    public static Timer flymer = new Timer();

    public static final Consumer<Robot> controlLiftUnboundedPID = r -> {

        liftPID.target += inputUtil.deadzoneStick(r.input.armController.getRightY()) * Robot.dt * ControlSettings.LIFT_PID_SETPOINT_COMPOUND_COEFFICIENT;

        if (liftPID.target < ControlSettings.LIFT_ENCODER_MINIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MINIMUM; }
        if (liftPID.target > ControlSettings.LIFT_ENCODER_MAXIMUM){ liftPID.target = ControlSettings.LIFT_ENCODER_MAXIMUM; }

        double PIDOut = -liftPID.tick(r.telescope.liftVal(), Robot.dt, false);

        //telemetryUtil.put(" lift pid raw output", PIDOut, Tabs.DEBUG);

        if (Math.abs(PIDOut) > ControlSettings.LIFT_MOTOR_MAX_OUTPUT) { PIDOut = PIDOut * (ControlSettings.LIFT_MOTOR_MAX_OUTPUT/Math.abs(PIDOut)); }

        //telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        //telemetryUtil.put("Arm PID target", liftPID.target, Tabs.DEBUG);

        PIDOut += -r.input.armController.getRightY() * ControlSettings.LIFT_PID_USER_MULTIPLIER;

        double pwr = inputUtil.deadzoneStick(-r.input.armController.getRightY())
            * ControlSettings.LIFT_MULT * 6;


        if (Robot.emergencyPIDstop){ r.telescope.liftMotor.set(pwr); }
        if (!Robot.emergencyPIDstop){ telescopeUtil.softHardLimitLift(r.telescope, PIDOut); }


    };


    public static final Consumer<Robot> controlLiftUnbounded = r -> {

        double pwr = inputUtil.deadzoneStick(-r.input.armController.getLeftY())
            * ControlSettings.LIFT_MULT * 6;

        r.telescope.liftMotor.set(pwr);
    };


    

    public static final Consumer<Robot> gyroPIDControl = r -> {

        if (gyroPID.target <= 0 && (inputUtil.deadzoneStick(r.input.armController.getLeftY()) >= 0)){ gyroPID.target = 0; }

        gyroPID.target += inputUtil.deadzoneStick(r.input.armController.getLeftY()) * Robot.dt * ControlSettings.LIFT_GYRO_SETPOINT_COMPOUND_COEFFICIENT;

        if (gyroPID.target < ControlSettings.LIFT_GYRO_MINIMUM){ gyroPID.target = ControlSettings.LIFT_GYRO_MINIMUM; }
        if (gyroPID.target > ControlSettings.LIFT_GYRO_MAXIMUM){ gyroPID.target = ControlSettings.LIFT_GYRO_MAXIMUM; }

        double PIDOut = -gyroPID.tick(r.gyro.getArm(), Robot.dt, false);

        telemetryUtil.put(" lift pid raw output", PIDOut, Tabs.DEBUG);

        //if (Math.abs(PIDOut) > maxOutput) { PIDOut = PIDOut * (maxOutput/Math.abs(PIDOut)); }

        telemetryUtil.put("Arm PID out", PIDOut, Tabs.DEBUG);
        telemetryUtil.put("Arm PID target", gyroPID.target, Tabs.DEBUG);

        PIDOut += -r.input.armController.getLeftY() * ControlSettings.GYRO_PID_USER_MULTIPLIER;

        if (PIDOut < -.4) { PIDOut = -.4; }
        if (PIDOut > ControlSettings.LIFT_MOTOR_MAX_OUTPUT) {PIDOut = ControlSettings.LIFT_MOTOR_MAX_OUTPUT;}


        double pwr = inputUtil.deadzoneStick(-r.input.armController.getLeftY())
            * ControlSettings.LIFT_MULT * 6;

        if ((inputUtil.deadzoneStick(r.input.armController.getLeftY()) >= 0) || r.input.armController.getXButtonPressed() || r.input.buttonPanel.getRawButtonPressed(6) ||
             r.input.armController.getYButtonPressed() || r.input.buttonPanel.getRawButtonPressed(3) || r.input.armController.getBButtonPressed() ||
             r.input.buttonPanel.getRawButtonPressed(4) || r.input.buttonPanel.getRawButtonPressed(5)){

                flymer.reset();
                flymer.start();

             }

        if (flymer.get() >= 2.00 && PIDOut <= .22){
            PIDOut *= .4;
        }

        telemetryUtil.put("Gyro pid target", gyroPID.target, Tabs.ROBOT);


        if (Robot.emergencyPIDstop){ r.telescope.liftMotor.set(pwr); }
        if (!Robot.emergencyPIDstop){ telescopeUtil.softHardLimitLift(r.telescope, PIDOut); }


    };
    
}
