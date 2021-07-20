package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.lib.geometry.Twist2d;
import frc.robot.Constants;
import frc.robot.Kinematics;
import frc.lib.util.DriveSignal;
import frc.lib.util.Util;
import edu.wpi.first.wpilibj.XboxController;

public class IntakeSubsystem extends Subsystem {
    public final TalonSRX mIntakeBottom = new TalonSRX(7);
    public final DigitalInput LimitSwitchTop = new DigitalInput(1);
    public final DigitalInput LimitSwitchBottom = new DigitalInput(2); 

    public synchronized void armMovement(boolean up, boolean down){
        if(up){
            mIntakeBottom.set(0.5);
            if(LimitSwitchTop == true){
                mIntakeBottom.set(0.0);
            }
        }else{
            mIntakeBottom.set(0.0);
        }
        if(down){
            mIntakeBottom.set(-0.3);
            if(LimitSwitchBottom == true){
                mIntakeBottom.set(0.0);
            }
        }else{
            mIntakeTop.set(0.0);
        }
    }
}