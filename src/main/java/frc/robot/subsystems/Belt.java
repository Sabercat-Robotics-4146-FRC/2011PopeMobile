package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Belt extends Subsystem {
    
    public static Belt mInstance;
    
    public final TalonSRX beltMotor = new TalonSRX(6);
    
    public static Belt getInstance() {
        if (mInstance == null) {
            mInstance = new Belt();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public double right_demand;
        public double left_demand;
    }

public void setBeltDirection(boolean in, boolean out){
    if(in && !out) {
        beltMotor.set(ControlMode.PercentOutput, 0.4);
    }
    else if(out && !in) {
        beltMotor.set(ControlMode.PercentOutput, -0.4);
    }
    else {
        beltMotor.set(ControlMode.PercentOutput, 0.0);
    }
}

@Override
public void stop() {
    beltMotor.set(ControlMode.PercentOutput,0.0);
}

@Override
public boolean checkSystem() {
    return true;
}

@Override
public void outputTelemetry() {

}

}
