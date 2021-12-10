package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Arm extends Subsystem {
    private static Arm mInstance;

    public static Arm getInstance() {
        if (mInstance == null) {
            mInstance = new Arm();
        }

        return mInstance;
    }

    private final TalonSRX mArmMotor;

    // probably add these IDs to Constants later
    private DigitalInput downLimit = new DigitalInput(0); // false=pushed
    private DigitalInput upLimit = new DigitalInput(1); // false=pushed

    private Arm() {
        mArmMotor = new TalonSRX(Constants.kIntakeArmMotorId);
    }

    public synchronized void setArm(boolean armUp, boolean armDown) {

        if (armDown&&(downLimit.get())) {
            mArmMotor.set(ControlMode.PercentOutput, .3); // go down
        } else if (armUp&&(upLimit.get())) {
            mArmMotor.set(ControlMode.PercentOutput, -.5); // go up
        } else {
            stop();
        } 

        SmartDashboard.putBoolean("Arm going up?: ", armUp);
        SmartDashboard.putBoolean("Arm going down?: ", armDown);
        SmartDashboard.putBoolean("Down limit switch: ", downLimit.get());
        SmartDashboard.putBoolean("Up limit switch: ", upLimit.get());
        // SmartDashboard.putNumber("Arm speed: ", ControlMode.PercentOutput);

    }

    @Override
    public void writePeriodicOutputs() {}

    @Override
    public void stop() {
        mArmMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}