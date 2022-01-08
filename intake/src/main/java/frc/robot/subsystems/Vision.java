package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;

public class Vision extends Subsystem {
    
    private static Vision mInstance;

    public static boolean object = false;
    
    public static Vision getInstance() {
        if (mInstance == null) {
            mInstance = new Vision();
        }
        return mInstance;
    }

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    // read values periodically

    public synchronized void limelightTurret(){
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry tv = table.getEntry("tv");
    
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double v = tv.getDouble(0.0);
        if(v == 1.0){   
            Robot.mTurret.turretAutomatic(x,y);
        }else{
            Robot.mTurret.turretMovement(Robot.mController.getRawAxis(3) - Robot.mController.getRawAxis(2));
        }
    }
    
    @Override
    public void stop() {        
    }
    @Override
    public boolean checkSystem() {
        return false;
    }
    @Override
    public void outputTelemetry() {
        //SmartDashboard.putNumber("Limelight x", tx.getDouble(0.0));
    }

}