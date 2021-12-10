package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;


public class Flywheel extends Subsystem {

    private static Flywheel mInstance;

    public static Flywheel getInstance() {
        if (mInstance == null) {
            mInstance = new Flywheel();
        }

        return mInstance;
    }

    private final TalonSRX mFlywheelMotor;

    double setpoint = 5000;

    private final PIDController pid = new PIDController(Constants.kFlywheelKp, Constants.kFlywheelKi, Constants.kFlywheelKd);

    private final class PeriodicIO {
        private double Velocity;
    }

    private final PeriodicIO mPeriodicIO = new PeriodicIO();

    private Flywheel() {
        mFlywheelMotor = new TalonSRX(Constants.kFlywheelId);
    }

    public synchronized void setFlywheel(boolean throttle) {

        if (throttle) {
            mPeriodicIO.Velocity = pid.calculate(mPeriodicIO.Velocity, setpoint*2.2);
        } else {
            mPeriodicIO.Velocity = 0;
        }

    }

    @Override
    public void writePeriodicOutputs() {
        mFlywheelMotor.set(ControlMode.Velocity, -mPeriodicIO.Velocity);
    }

    @Override
    public void stop() {
        mFlywheelMotor.set(ControlMode.Velocity, 0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}