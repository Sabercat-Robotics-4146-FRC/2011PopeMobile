package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Belt extends Subsystem {
    private static Belt mInstance;

    public static Belt getInstance() {
        if (mInstance == null) {
            mInstance = new Belt();
        }

        return mInstance;
    }

    private final TalonSRX mBeltMotor;

    private Belt() {
        mBeltMotor = new TalonSRX(Constants.kIntakeBeltMotorId);
    }

    public synchronized void setBelt(boolean clockwise, boolean counterclockwise) {
        
        if (clockwise && !counterclockwise) {
            mBeltMotor.set(ControlMode.PercentOutput, 0.5);
        } else if (counterclockwise && !clockwise) {
            mBeltMotor.set(ControlMode.PercentOutput, -0.5);
        } else {
            stop();
        }

    }

    @Override
    public void writePeriodicOutputs() {}

    @Override
    public void stop() {
        mBeltMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}