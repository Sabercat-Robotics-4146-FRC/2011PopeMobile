package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSubsystem extends Subsystem {

    private static IntakeSubsystem mInstance;
    
    public static IntakeSubsystem getInstance() {
        if (mInstance == null) {
            mInstance = new IntakeSubsystem();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public double intake_demand;
    }

    private PeriodicIO mPeriodicIO = new PeriodicIO();


    public final TalonSRX mIntakeMotor = new TalonSRX(7);
    public final DigitalInput LimitSwitchTop = new DigitalInput(1);
    public final DigitalInput LimitSwitchBottom = new DigitalInput(0); 

    public synchronized void armMovement(boolean up, boolean down){
        if(up){
            mPeriodicIO.intake_demand = -0.3;
            if(!LimitSwitchTop.get()){
                mPeriodicIO.intake_demand = 0;
            }
        }
        else if(down){
            mPeriodicIO.intake_demand = 0.5;
            if(!LimitSwitchBottom.get()){
                mPeriodicIO.intake_demand = 0;
            }
        }
        else{
            mPeriodicIO.intake_demand = 0;
        }
    }

    public void writePeriodicOutputs() {
        mIntakeMotor.set(ControlMode.PercentOutput, mPeriodicIO.intake_demand);
    }

    @Override
    public void stop() {
        // TODO stop intake motors
        mIntakeMotor.set(ControlMode.PercentOutput, 0.0);

    }

    @Override
    public boolean checkSystem() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void outputTelemetry() {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber("Motor Output: ", mIntakeMotor.getMotorOutputPercent());
        SmartDashboard.putBoolean("lim up", LimitSwitchTop.get());
        SmartDashboard.putBoolean("lim down", LimitSwitchBottom.get());

    }
}