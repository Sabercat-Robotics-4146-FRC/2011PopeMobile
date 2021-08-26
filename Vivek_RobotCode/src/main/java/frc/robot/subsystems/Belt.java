package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.subsystems.Drive;
public class Belt extends Subsystem{
    private DigitalInput topLimitSwitch = new DigitalInput(1);
    private DigitalInput bottomLimitSwitch = new DigitalInput(0);
    
     
    TalonSRX leverMotor = new TalonSRX(6);
    private static Belt mInstance;
    public static Belt getInstance() {
        
        if (mInstance == null) {
            mInstance = new Belt();
            
        }

        return mInstance;
    }

    public synchronized void toggleBelt(boolean counter, boolean clock) {
        if(counter && !clock) {
            leverMotor.set(ControlMode.PercentOutput, -0.4);
        }
        else if(clock && !counter) {
            leverMotor.set(ControlMode.PercentOutput, 0.4);
        }
        else leverMotor.set(ControlMode.PercentOutput,0.0);
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
