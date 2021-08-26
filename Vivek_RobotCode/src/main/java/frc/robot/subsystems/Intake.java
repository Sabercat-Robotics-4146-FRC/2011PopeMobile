package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.subsystems.Drive;
public class Intake extends Subsystem {
    private DigitalInput topLimitSwitch = new DigitalInput(1);
    private DigitalInput bottomLimitSwitch = new DigitalInput(0);
    TalonSRX leverMotor = new TalonSRX(7);
    private static Intake mInstance;
    public static Intake getInstance() {
        
        if (mInstance == null) {
            mInstance = new Intake();
            
        }

        return mInstance;
    }
    public synchronized void toggleArm(boolean up, boolean down) {
        
        
        if(up) {
            leverMotor.set(ControlMode.PercentOutput,-0.5);
            if(!topLimitSwitch.get()) leverMotor.set(ControlMode.PercentOutput,0.0);
        }
        else if(down) {
            leverMotor.set(ControlMode.PercentOutput,0.3);
            if(!bottomLimitSwitch.get()) leverMotor.set(ControlMode.PercentOutput, 0.0);
        }
        else leverMotor.set(ControlMode.PercentOutput,0);
    }
    public boolean checkSystem() {
        return true;
    }

    public void outputTelemetry() {
        
    }
    public void stop(){
        leverMotor.set(ControlMode.PercentOutput, 0);
    }
       
    }


