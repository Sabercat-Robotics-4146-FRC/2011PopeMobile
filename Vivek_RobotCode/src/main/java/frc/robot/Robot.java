package frc.robot;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.loops.Looper;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Turret;


public class Robot extends TimedRobot {
	Looper mEnabledLooper = new Looper();
	Looper mDisabledLooper = new Looper();
	
	private SubsystemManager mSubsystemManager = SubsystemManager.getInstance();

	private Drive mDrive;
	private Intake mIntake;
	private Belt mBelt;
	private Turret mTurret;
	private XboxController mDriveStick;
	
	public static boolean xButtonFlag,xButton,bButtonFlag,bButton;
	

	@Override
	public void robotInit() {
		mDrive = Drive.getInstance();
		mIntake = Intake.getInstance();
		mBelt = Belt.getInstance();
		mTurret = Turret.getInstance();
		mSubsystemManager.setSubsystems(mDrive);
		mSubsystemManager.setSubsystems(mIntake);
		mDriveStick = new XboxController(Constants.kDriveStickPort);

		mSubsystemManager.registerEnabledLoops(mEnabledLooper);
		mSubsystemManager.registerDisabledLoops(mDisabledLooper);
	}

	@Override
	public void autonomousInit() {
		mDisabledLooper.stop();
		mEnabledLooper.start();
	}

	@Override
	public void disabledInit() {
		mEnabledLooper.stop();
		mDisabledLooper.start();
	}

	@Override
	public void teleopInit() {
		mDisabledLooper.stop();
		mEnabledLooper.start();
	}

	@Override
	public void teleopPeriodic() {
		//mDrive.setCheesyishDrive(mThrottleStick.getRawAxis(1), -mTurnStick.getRawAxis(0), mTurnStick.getRawButton(1));
		mDrive.setCheesyishDrive(mDriveStick.getRawAxis(1), -mDriveStick.getRawAxis(4), mDriveStick.getRawButton(1));
		
		if(!xButtonFlag && mDriveStick.getRawButton(3)) {
			xButton = !xButton;
			xButtonFlag = true;
		}
		else if(xButtonFlag && !mDriveStick.getRawButton(3)) xButtonFlag = false;
		if(!bButtonFlag && mDriveStick.getRawButton(2)) {
			bButton = !bButton;
			bButtonFlag = true;
		}
		else if(bButtonFlag && !mDriveStick.getRawButton(3)) bButtonFlag = false;
		
		if(mDriveStick.getRawAxis(2) > 0 && mDriveStick.getRawAxis(3) == 0) mTurret.turretRotation(-mDriveStick.getRawAxis(2));
		else if(mDriveStick.getRawAxis(3) > 0 && mDriveStick.getRawAxis(2) == 0) mTurret.turretRotation(mDriveStick.getRawAxis(3));
		else mTurret.stop();

		if(mDriveStick.getRawButton(5)) mIntake.toggleArm(true, false);
		else if(mDriveStick.getRawButton(6)) mIntake.toggleArm(false, true);
		else mIntake.stop();
		
		mBelt.toggleBelt(bButton,xButton);
		
		
		
	}
}

