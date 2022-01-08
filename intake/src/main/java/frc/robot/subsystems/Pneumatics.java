package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics extends Subsystem {
    
    private static Pneumatics mInstance;
    
    public static Pneumatics getInstance() {
        if (mInstance == null) {
            mInstance = new Pneumatics();
        }

        return mInstance;
    }

    public final Solenoid piston = new Solenoid(0);
    public final Compressor c = new Compressor(0);

    boolean enable = c.enabled();

    public synchronized void pistonToggle(boolean toggle){
        if(toggle){
            piston.set(true);
        }else{
            piston.set(false);
        }
    }

    @Override
    public void stop() {
        piston.set(false);

    }

    @Override
    public boolean checkSystem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void outputTelemetry() {
        // TODO Auto-generated method stub

    }

}
