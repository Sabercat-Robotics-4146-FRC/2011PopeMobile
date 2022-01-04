package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Subsystem{
    public static Limelight mInstance;
    
    public static Limelight getInstance() {
        if (mInstance == null) {
            mInstance = new Limelight();
        }
        return mInstance;
    }
NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
//double tv = table.getEntry("tv").getDouble(0);
NetworkTableEntry tv = table.getEntry("tv");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");
NetworkTableEntry ledMode = table.getEntry("ledMode");
NetworkTableEntry camMode = table.getEntry("camMode");
NetworkTableEntry pipeline = table.getEntry("pipeline");
NetworkTableEntry stream = table.getEntry("stream");

double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);

/*double Kp = -0.1;
double min_command = 0.05;
double heading_error = -x;
double steering_adjust = 0.0;*/
TalonSRX mTurretMotor = new TalonSRX(8);

public void trackLimelight(boolean activate) {
    if (activate){
        if(x > 0.0){
            mTurretMotor.set(ControlMode.PercentOutput, -Math.abs(x/27.0));
        } else if (x < 0.0){
            mTurretMotor.set(ControlMode.PercentOutput, Math.abs(x/27.0));
        } else {
            mTurretMotor.set(ControlMode.PercentOutput, 0.0);
        }
}
}
@Override
  public void readPeriodicInputs() {
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
}
    @Override
    public void writePeriodicOutputs() {
    }

    @Override
    public void stop() {
    }

    public void outputTelemetry(){
SmartDashboard.putNumber("LimelightX", x);
SmartDashboard.putNumber("LimelightY", y);
SmartDashboard.putNumber("LimelightArea", area);
}

@Override
public boolean checkSystem() {
    return true;
}
}
