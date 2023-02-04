/*package frc.robot.Simulation;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.simulation.DoubleSolenoidSim;
import edu.wpi.first.wpilibj.simulation.PWMSim;
import frc.robot.Constants;
import frc.robot.functions.armUtil;
import frc.robot.subsystems.IntakeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulationIntake {
    
    static final double DELTA = 1e-2; // acceptable deviation range?



    IntakeData intake;
    armUtil intakeUtil;
    PWMSim simMotor;
    DoubleSolenoidSim simPiston;

    @BeforeEach
    void setup(){

        assert HAL.initialize(500, 0);
        intake = new IntakeData();
        intakeUtil = new armUtil();
        simMotor = new PWMSim(Constants.EXTEND_INTAKE_PORT);
        simPiston = new DoubleSolenoidSim(PneumaticsModuleType.CTREPCM, 
        intake.armSolenoid.getFwdChannel(),
        intake.armSolenoid.getRevChannel());
    
    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @AfterEach
    void shutdown() throws Exception{

        intakeUtil.close();

    }

    @Test
    void doesntWorkWhenClosed(){

        intakeUtil.retractAll(intake);
        intakeUtil.activate(intake, 0.5);
        assertEquals(0.0, simMotor.getSpeed(), DELTA);

    }

    @Test
    void worksWhenOpen(){

        intakeUtil.extendAll(intake);
        intakeUtil.activate(intake, DELTA);
        assertEquals(0.5, simMotor.getSpeed(), DELTA);

    }

    @Test
    void retractTest(){

        intakeUtil.retractAll(intake);
        assertEquals(DoubleSolenoid.Value.kReverse, simPiston.get());

    }

    @Test
    void deployTest(){

        intakeUtil.extendAll(intake);
        assertEquals(DoubleSolenoid.Value.kForward, simPiston.get());

    }

    

}*/
