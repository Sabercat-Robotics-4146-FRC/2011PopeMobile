package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.subsystems.Drive;

public class Turret extends Subsystem{
    private static Turret mInstance;
    private DigitalInput left = new DigitalInput(2);
    private DigitalInput right = new DigitalInput(3);
    private TalonSRX mTurret = new TalonSRX(8);
    public static Turret getInstance() {
        
        if (mInstance == null) {
            mInstance = new Turret();
            
        }

        return mInstance;
    }
    public synchronized void turretRotation(double rotRight) {
        
        if(rotRight < 0) {
            mTurret.set(ControlMode.PercentOutput,rotRight);
            if(!left.get()) mTurret.set(ControlMode.PercentOutput,0.0);
        }
        else if(rotRight > 0) {
            mTurret.set(ControlMode.PercentOutput,rotRight);
            if(!right.get()) mTurret.set(ControlMode.PercentOutput, 0.0);
        }
        else mTurret.set(ControlMode.PercentOutput,0);
    }
    public boolean checkSystem() {
        return true;
    }

    public void outputTelemetry() {
        
    }
    public void stop(){
        mTurret.set(ControlMode.PercentOutput, 0);
    }
}
