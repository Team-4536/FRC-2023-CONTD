package frc.robot.controllers;

import frc.robot.Robot;
import frc.robot.functions.gyroUtil;
import frc.robot.subsystems.GyroData;

public class balenceController {
    double originalAngle;
    double p;

    public balenceController(double angle, double p){
        this.originalAngle = angle;
        this.p = p;
    }

    public double tick(double angle){
        if(angle < 175){
            return p;
        }
        if(angle > 185){
            return p * -1;
        }
        if(angle > 175 && angle < 185){
            return 0;
        }

        return 0;
    }
}
