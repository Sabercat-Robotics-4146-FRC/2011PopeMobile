package frc.robot.subsystems;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;


public class Turret extends Subsystem {
    private static Turret mInstance;

    public static Turret getInstance() {
        if (mInstance == null) {
            mInstance = new Turret();
        }

        return mInstance;
    }

    private final TalonSRX mTurretMotor;

    private DigitalInput limitLeft = new DigitalInput(Constants.kLimitSwitchLeftId);
    private DigitalInput limitRight = new DigitalInput(Constants.kLimitSwitchRightId);

    double setpoint = 0;
    double demand;

    private final PIDController pid = new PIDController(Constants.kTurretKp, Constants.kTurretKi, Constants.kTurretKd);


    private Turret() {
        mTurretMotor = new TalonSRX(Constants.kTurretMotorId);
    }

    public synchronized void setTurretManual(double throttle) {

        mTurretMotor.set(ControlMode.PercentOutput, throttle);
        
        if (limitRight.get() && throttle < 0) {
            stop();
        } else if (limitLeft.get() && throttle > 0) {
            stop();
        }

    }

    public synchronized void setTurretAuto(double xOffset) {

        demand = pid.calculate(xOffset, setpoint);
        mTurretMotor.set(ControlMode.PercentOutput, demand);
        
        if (limitRight.get() && demand < 0) {
            stop();
        } else if (limitLeft.get() && demand > 0) {
            stop();
        }

    }

    @Override
    public void writePeriodicOutputs() {}

    @Override
    public void stop() {
        mTurretMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}