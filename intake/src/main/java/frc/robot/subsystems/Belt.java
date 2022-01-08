package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Belt extends Subsystem {

    private static Belt mInstance;

    public static Belt getInstance() {
        if(mInstance == null){
            mInstance = new Belt();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public double belt_demand;
    }

    private PeriodicIO mPeriodicIO = new PeriodicIO();

    public final TalonSRX mBeltMotor = new TalonSRX(6);

    public void writePeriodicOutputs() {
        mBeltMotor.set(ControlMode.PercentOutput, mPeriodicIO.belt_demand);
    }

    public synchronized void beltRotation(boolean clockwise, boolean counter_clockwise) {
        if(clockwise && counter_clockwise){
            mPeriodicIO.belt_demand = 0;
        }else if (!clockwise && !counter_clockwise){
            mPeriodicIO.belt_demand = 0;
        }else if(clockwise){
            mPeriodicIO.belt_demand = 0.4;
        }else if(counter_clockwise){
            mPeriodicIO.belt_demand = -0.4;
        }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        mPeriodicIO.belt_demand = 0;
    }

    @Override
    public boolean checkSystem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void outputTelemetry() {
        // TODO Auto-generated method stub

    }
    
}
