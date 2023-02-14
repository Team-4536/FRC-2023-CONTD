package frc.robot.functions;

import frc.robot.subsystems.DriveData;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class driveUtil {




    public static void setPowerTank(DriveData drive, double vertical, double turning, double scale) {
        setPowerMechanum(drive, 0, vertical, turning, scale);
    }




    public static void setPowerMechanum(DriveData drive, double x, double y, double turning, double scale) {


        double[] speeds = {
                (y + x - turning), //front left
                (y - x + turning), //front right
                (y - x - turning), //back left
                (y + x + turning), //back right
        };

        //scale *= -1;


        double max = Math.abs(speeds[0]);
        for (int i = 1; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i])) {
                max = Math.abs(speeds[i]);
            }
        }

        //turn -1, 1 joystick into a scale based on upper and lower bound constants
        //double scaleBound = Constants.POWER_SCALE_UPPER_BOUND - Constants.POWER_SCALE_LOWER_BOUND;
        //scale = ((scale + 1.0) / (2/scaleBound)) + Constants.POWER_SCALE_LOWER_BOUND;
        //scale = 1;


        //scale all sppeds so that the fastest is now going at 1
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) {
                speeds[i] /= max; } }

        //scale speeds again based on the power scaling value
        for (int i = 0; i < speeds.length; i++){
            speeds[i] = speeds[i] * scale;
        }

        SmartDashboard.putNumber("scale", scale);

        drive.frontLeftDrive.set(speeds[0]);
        drive.frontRightDrive.set(speeds[1]);
        drive.backLeftDrive.set(speeds[2]);
        drive.backRightDrive.set(speeds[3]);

    }




    public static void setPowerUniform(DriveData drive, double power) {
        drive.frontLeftDrive.set(power);
        drive.frontRightDrive.set(power);
        drive.backLeftDrive.set(power);
        drive.backRightDrive.set(power);
    }

    public static void tankLR(DriveData drive, double leftPower, double rightPower) {
        drive.frontLeftDrive.set(leftPower);
        drive.frontRightDrive.set(rightPower);
        drive.backLeftDrive.set(leftPower);
        drive.backRightDrive.set(rightPower);
    }

    public static void stop(DriveData drive){
        setPowerUniform(drive, 0.0);
    }

    public static void testFL(DriveData drive, double pow){
        drive.frontLeftDrive.set(pow);
    }

    public static void testBL(DriveData drive, double pow){
        drive.backLeftDrive.set(pow);
    }

    public static void testFR(DriveData drive, double pow){
        drive.frontRightDrive.set(pow);
    }

    public static void testBR(DriveData drive, double pow){
        drive.backRightDrive.set(pow);
    }




}
