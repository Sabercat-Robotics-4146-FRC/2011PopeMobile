package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.Constants;


public class Piston extends Subsystem {

    private static Piston mInstance;

    public static Piston getInstance() {
        if (mInstance == null) {
            mInstance = new Piston();
        }

        return mInstance;
    }

    private final Compressor compressor;
    private final Solenoid piston;

    private Piston() {
        compressor = new Compressor(Constants.kPistonCompressorId);
        piston = new Solenoid(Constants.kPistonSolenoidId);
    }

    public synchronized void setPiston(boolean button) {
        compressor.setClosedLoopControl(true);
        piston.set(button);
    }

    @Override
    public void stop() {
        piston.set(false);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {}

}