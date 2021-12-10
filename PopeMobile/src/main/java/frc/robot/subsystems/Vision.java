package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Subsystem {

    private static Vision mInstance;

    public static Vision getInstance() {
        if (mInstance == null) {
            mInstance = new Vision();
        }

        return mInstance;
    }

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    public double v = tv.getDouble(0.0);
    public double x = tx.getDouble(0.0);
    public double y = ty.getDouble(0.0);
    public double area = ta.getDouble(0.0);

    @Override
    public void stop() {}

    @Override
    public void readPeriodicInputs() {
        v = tv.getDouble(0.0);
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}
