package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Piston extends Subsystem{
    public static Piston mInstance;
    
    public static Piston getInstance() {
        if (mInstance == null) {
            mInstance = new Piston();
        }

        return mInstance;
    }

private static Compressor compressor;
private static Solenoid solenoid;


private Piston(){
    compressor = new Compressor(0);
    solenoid = new Solenoid(0);
}


boolean enabled = compressor.enabled();
boolean pressureSwitch = compressor.getPressureSwitchValue();
double current = compressor.getCompressorCurrent();

public void togglePiston(boolean toggle){
    if(toggle){
        solenoid.set(true);  
    } else {
        solenoid.set(false);
    }
}

@Override
    public void writePeriodicOutputs() {
        compressor.setClosedLoopControl(true);
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
   //SmartDashboard.putBoolean("PistonUp",  Solenoid.set(true));
}

}
