package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Flywheel extends Subsystem{
    public static Flywheel mInstance;
    
    public static Flywheel getInstance() {
        if (mInstance == null) {
            mInstance = new Flywheel();
        }

        return mInstance;
    }

    public final TalonSRX flyWheelMotor = new TalonSRX(12);
    private double setpoint = 500.0;
    
    public static class PeriodicIO {
        public double motor_demand;    
    } 
    
    public void PIDCalculate(){
        flyWheelMotor.configFactoryDefault();
        flyWheelMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        
        flyWheelMotor.setSensorPhase(true);
       
        flyWheelMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		flyWheelMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		flyWheelMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		flyWheelMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		flyWheelMotor.config_kF(Constants.kPIDLoopIdx, Constants.kFlywheelKf, Constants.kTimeoutMs);
		flyWheelMotor.config_kP(Constants.kPIDLoopIdx, Constants.kFlywheelKp, Constants.kTimeoutMs);
		flyWheelMotor.config_kI(Constants.kPIDLoopIdx, Constants.kFlywheelKi, Constants.kTimeoutMs);
		flyWheelMotor.config_kD(Constants.kPIDLoopIdx, Constants.kFlywheelKd, Constants.kTimeoutMs);
    }

    private static PeriodicIO mPeriodicIO = new PeriodicIO();

    public void setFlywheelSpinning(boolean spinning) {
        if(spinning){
            mPeriodicIO.motor_demand = setpoint * 4096 / 600;
       flyWheelMotor.set(ControlMode.Velocity, -mPeriodicIO.motor_demand);
        } else {
            flyWheelMotor.set(ControlMode.Velocity, 0);
        }
    }
   
    @Override
  public void readPeriodicInputs() {
  }
    @Override
    public void writePeriodicOutputs() {
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {
    SmartDashboard.putNumber("RPM", flyWheelMotor.getSelectedSensorVelocity());
    }
}
