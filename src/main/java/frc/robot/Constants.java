package frc.robot;

public class Constants {
    public static final double kLooperDt = 0.01;

    // CAN
    public static final int kLongCANTimeoutMs = 100; // use for constructors
    public static final int kCANTimeoutMs = 10; // use for important on the fly updates

    // Pneumatics
    public static final int kPCMId = 0;

    // Drive
    public static final int kDriveRightMasterId = 1;
    public static final int kDriveRightSlaveId = 2;

    public static final int kDriveLeftMasterId = 3;
    public static final int kDriveLeftSlaveId = 4;

    public static final double kDriveWheelTrackWidthInches = 24.98;
    public static final double kTrackScrubFactor = 1.0469745223;

    // Joysticks
    public static final int kDriveStickPort = 0;


    // Flywheel
    public static final int kFlywheelMasterId = 4;
    public static final int kFlywheelSlaveId = 5;
    public static final double kFlywheelKp = 0.25;
    public static final double kFlywheelKi = 0.001;
    public static final double kFlywheelKd = 10.0;
    public static final double kFlywheelKf = 5000.0/7200;
    public static final double kFlywheelTicksPerRevolution = 360.0; // based on gear reduction between encoder and output shaft, and encoder ppr
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;

    // Gear Grabber
    public static final int kMotorGearGrabberTalonId = 6;
    public static final int kMotorGearGrabberSolenoidId = 1;

    // Intake
    public static final int kIntakeLeftTalonId = 7;
    //public static final int kIntakeRightTalonId = 8;
    public static final int kIntakeCloseSolenoidId = 2;
    public static final int kIntakeClampSolenoidId = 3;
}