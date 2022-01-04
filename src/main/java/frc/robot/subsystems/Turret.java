package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;


public class Turret extends Subsystem {

    public final TalonSRX turretMotor = new TalonSRX(8);
    private DigitalInput leftLimitSwitch = new DigitalInput(3);
    private DigitalInput rightLimitSwitch = new DigitalInput(2);

    public static Turret mInstance;
    
    public static Turret getInstance() {
        if (mInstance == null) {
            mInstance = new Turret();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public double motor_demand;
    }

private static PeriodicIO mPeriodicIO = new PeriodicIO();

public void setTurretRotation(boolean clockwise, boolean counterClockwise){
    if(clockwise){
        mPeriodicIO.motor_demand = -.2;
        if(rightLimitSwitch.get() == true){
            mPeriodicIO.motor_demand = 0.0;
        }
    }
    else if(counterClockwise){
       mPeriodicIO.motor_demand = 0.2;
    if(leftLimitSwitch.get() == true){
        mPeriodicIO.motor_demand = 0.0;
    }
    }
    else{
        mPeriodicIO.motor_demand = 0.0;
}
}

    @Override
    public void writePeriodicOutputs() {
        turretMotor.set(ControlMode.PercentOutput, mPeriodicIO.motor_demand);
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
        SmartDashboard.putNumber("MotorMoving", mPeriodicIO.motor_demand);
        SmartDashboard.putBoolean("RightLimitSwitch", rightLimitSwitch.get()); 
        SmartDashboard.putBoolean("LeftLimitSwitch", leftLimitSwitch.get());
    }
    
}
