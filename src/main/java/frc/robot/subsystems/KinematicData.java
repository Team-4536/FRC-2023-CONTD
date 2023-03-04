package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.constants.Hardware;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.telemetryUtil.Tabs;
import frc.robot.subsystems.TelescopeData;
import frc.robot.functions.telescopeUtil;
import frc.robot.utils.V2d;
import java.lang.Math;

public class KinematicData{
    public KinematicData(){

    }
    static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
    static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
    
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
    
        return result;
    }
    public static double[][] topOfArmPos(double armLength, double armAngle){
        //Arm angle must be a degree NOT a radian
        //arm length must be in centimeters
        //V2d backCenterPos = new V2d(0, 0);
        double backCenterPos[][] = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        double T01[][] = {
            {1, 0, Hardware.ARM_BASE.x},
            {0, 1, Hardware.ARM_BASE.y},
            {0, 0, 1                  }};
        
        double T12[][] = {
            {Math.cos(Math.toRadians(armAngle)), -Math.sin(Math.toRadians(armAngle)), armLength * Math.cos(Math.toRadians(armAngle))},
            {Math.sin(Math.toRadians(armAngle)), Math.cos(Math.toRadians(armAngle)), armLength * Math.sin(Math.toRadians(armAngle))},
            {0, 0, 1}};

        double T02[][] = multiplyMatrices(T01, T12);
        
        double location[][] = multiplyMatrices(backCenterPos, T02);
        return location;
    }
    public void sendTelemetry(){
        telemetryUtil.put("Position of Arm", KinematicData.topOfArmPos(telescopeUtil.armDistanceByEncoderCm(Robot.instance.telescope.retractVal()), 0), Tabs.ROBOT);
        telemetryUtil.put("retractVal", Robot.instance.telescope.retractVal(), Tabs.ROBOT);
        //133 is the smallest it can be
    }



}