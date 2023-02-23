package frc.robot.functions;

import frc.robot.Robot;
import frc.robot.constants.ControlSettings;
import frc.robot.controllers.PIDController;
import frc.robot.subsystems.DriveData;

public final class driveUtil {

    public static PIDController pid = new PIDController(0.01, 0.0, 0.0);




    public static void setPowerTank(DriveData drive, double vertical, double turning, double scale) {
        setPowerMechanum(drive, 0, vertical, turning, scale);
    }

    public static void setPowerTankLR(DriveData drive, double leftPower, double rightPower) {
        drive.FLDrive.set(leftPower);
        drive.FRDrive.set(rightPower);
        drive.BLDrive.set(leftPower);
        drive.BRDrive.set(rightPower);
    }


    public static void setPowerMechPID(Robot r, double x, double y, double scale) {

        pid.target = gyroUtil.wrapAngle(pid.target);

        double drivePIDOut = pid.tick(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle()), Robot.dt, true);

        double pwr = drivePIDOut;
        if(pwr > ControlSettings.DRIVE_PID_CLAMP) { pwr = ControlSettings.DRIVE_PID_CLAMP; }
        if(pwr < -ControlSettings.DRIVE_PID_CLAMP) { pwr = -ControlSettings.DRIVE_PID_CLAMP; }

        driveUtil.setPowerMechanum(r.drive, x, y, pwr, scale);
    }

    public static void setPowerMechanum(DriveData drive, double x, double y, double turning, double scale) {


        double[] speeds = {
                (y + x + turning), //front left
                (y - x - turning), //front right
                (y - x + turning), //back left
                (y + x - turning), //back right
        };

        double max = Math.abs(speeds[0]);
        for (int i = 1; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i])) {
                max = Math.abs(speeds[i]);
            }
        }

        //scale all sppeds so that the fastest is now going at 1
        //scale speeds again based on the power scaling value
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) {
                speeds[i] /= max;
                speeds[i] *= scale;
            }
        }

        drive.FLDrive.set(speeds[0]);
        drive.FRDrive.set(speeds[1]);
        drive.BLDrive.set(speeds[2]);
        drive.BRDrive.set(speeds[3]);
    }

    public static void setPowerUniform(DriveData drive, double power) {
        drive.FLDrive.set(power);
        drive.FRDrive.set(power);
        drive.BLDrive.set(power);
        drive.BRDrive.set(power);
    }

    public static void stop(DriveData drive){
        setPowerUniform(drive, 0.0);
    }

}
