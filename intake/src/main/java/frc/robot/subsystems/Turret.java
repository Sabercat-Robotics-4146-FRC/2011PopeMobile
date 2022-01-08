package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Turret extends Subsystem{

    private static Turret mInstance;
    
    public static Turret getInstance() {
        if (mInstance == null) {
            mInstance = new Turret();
        }

        return mInstance;
    }

    public final TalonSRX turretMotor = new TalonSRX(8);
    public final DigitalInput LimitSwitchLeft = new DigitalInput(3);
    public final DigitalInput LimitSwitchRight = new DigitalInput(2);
    public synchronized void turretMovement(double right){
        turretMotor.set(ControlMode.PercentOutput, right);

        if(LimitSwitchRight.get() && right < 0){
            turretMotor.set(ControlMode.PercentOutput, 0.0);
        }else if(LimitSwitchRight.get() && right > 0){
            turretMotor.set(ControlMode.PercentOutput, right);
        }else if(LimitSwitchLeft.get() && right > 0){
            turretMotor.set(ControlMode.PercentOutput, 0.0);
        }else if(LimitSwitchLeft.get() && right < 0){
            turretMotor.set(ControlMode.PercentOutput, right);
        }
        
    }
    public synchronized void turretAutomatic(double x, double y){
        if(Math.abs(x) <= 0){
            turretMotor.set(ControlMode.PercentOutput, 0.0);
        }else{
            if(LimitSwitchLeft.get() && x < 0){
                turretMotor.set(ControlMode.PercentOutput, 0.0);
            }else if(LimitSwitchLeft.get() && x > 0 && x > 2){
                turretMotor.set(ControlMode.PercentOutput, -Math.abs(x/27));
            }else if(LimitSwitchRight.get() && x > 0){
                turretMotor.set(ControlMode.PercentOutput, 0.0);
            }else if(LimitSwitchRight.get() && x < 0 && x < 2){
                turretMotor.set(ControlMode.PercentOutput, Math.abs(x/27.5));
            }else if(!LimitSwitchLeft.get() && !LimitSwitchRight.get() && x>2){
                turretMotor.set(ControlMode.PercentOutput, -Math.abs(x/27));
            }else if(!LimitSwitchLeft.get() && !LimitSwitchRight.get() && x<2){
                turretMotor.set(ControlMode.PercentOutput, Math.abs(x/27));
            }
        }
    }
    @Override
    public void stop() {
        // TODO Auto-generated method stub
        turretMotor.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean checkSystem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void outputTelemetry() {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber("Motor Output: ", turretMotor.getMotorOutputPercent());
    }
}
