package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Flywheel extends Subsystem {
    private static Flywheel mInstance;
    PIDController pid = new PIDController(Constants.kFlywheelKp, Constants.kFlywheelKi, Constants.kFlywheelKd, Constants.kFlywheelKf);
    
    public static Flywheel getInstance() {
        if (mInstance == null) {
            mInstance = new Flywheel();
        }

        return mInstance;
    }

    public class PeriodicIO {
        
        double velocity_ticks_per_100_ms = 0.0;
        double setpoint = 500;
    
        double demand = 500;
    }
    private PeriodicIO mPeriodicIO = new PeriodicIO();

    public final TalonSRX flywheelMotor;

    private Flywheel() {
        flywheelMotor = new TalonSRX(12);

        flywheelMotor.configSelectedFeedbackSensor(
            FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kLongCANTimeoutMs);

    // set gains
    }
    public synchronized void flywheelRotation(boolean button, double setpoint){
      System.out.println(nativeUnitsToRPM(flywheelMotor.getSelectedSensorVelocity()));
      if(button)
        flywheelMotor.set(ControlMode.Velocity, -pid.calculate(nativeUnitsToRPM(flywheelMotor.getSelectedSensorVelocity()),(setpoint+692.578)/0.850775));
      else
        flywheelMotor.set(ControlMode.PercentOutput, 0);
    }
    public void readPeriodicInputs() {
        mPeriodicIO.velocity_ticks_per_100_ms = flywheelMotor.getSelectedSensorPosition(0);
    }

    public void writePeriodicOutputs() { 
      System.out.println(nativeUnitsToRPM(flywheelMotor.getSelectedSensorVelocity()));
      }
    
      @Override
      public void stop() {
        flywheelMotor.set(ControlMode.PercentOutput, 0.0);
      }
    
      @Override
      public boolean checkSystem() {
        return false;
      }
    
      public synchronized void setRPM(double rpm) {
        mPeriodicIO.demand = rpmToNativeUnits(rpm);
      }
    
      public synchronized double getRPM() {
        return nativeUnitsToRPM(getVelocityNativeUnits());
      }
    
      public synchronized double getVelocityNativeUnits() { 
        return mPeriodicIO.velocity_ticks_per_100_ms;
      }
    
      /**
       * @param ticks per 100 ms
       * @return rpm
       */
      public double nativeUnitsToRPM(double ticks_per_100_ms) {
        return ticks_per_100_ms * 10.0 * 60.0 / Constants.kFlywheelTicksPerRevolution;
      }
    
      /**
       * @param rpm
       * @return ticks per 100 ms
       */
      public double rpmToNativeUnits(double rpm) {
        return rpm / 60.0 / 10.0 * Constants.kFlywheelTicksPerRevolution;
      }
    
      @Override
      public void outputTelemetry() {
        SmartDashboard.putNumber("Flywheel RPM", nativeUnitsToRPM(flywheelMotor.getSelectedSensorVelocity()));
      }
    
}
