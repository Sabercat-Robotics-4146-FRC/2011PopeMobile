package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;


public class Intake2 extends Subsystem {
    
    private static Intake2 mInstance;

    public static Intake2 getInstance() {
        if (mInstance == null) {
            mInstance = new Intake2();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public double right_demand;
        public double left_demand;
    }
    private DigitalInput topLimitSwitch = new DigitalInput(1);
    private DigitalInput bottomLimitSwitch = new DigitalInput(0);
    public final TalonSRX armPivot = new TalonSRX(7);


       
    public void setArmPosition(boolean up, boolean down) {
        if(up) {
            armPivot.set(ControlMode.PercentOutput, -0.7);
            if(!topLimitSwitch.get()) {
                armPivot.set(ControlMode.PercentOutput, 0);
            }
    //If button is pressed send arm upwards, check when top limit switch is triggered then set motor speed to 0 when it is
        }
        else if(down) {
            armPivot.set(ControlMode.PercentOutput, 0.3);

            if(!bottomLimitSwitch.get()) {
                armPivot.set(ControlMode.PercentOutput, 0);
            }
    //If button is pressed send arm downwards, check when bottom limit switch is triggered then set motor speed to 0        
        }
        else {
            armPivot.set(ControlMode.PercentOutput, 0.0);
        }
    //if neither instance is true set motor speed to 0
    }
public void stop(){
    armPivot.set(ControlMode.PercentOutput, 0.0);
}
public boolean checkSystem(){
    return true;
}
public void outputTelemetry(){  
    }
}     

