/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.     
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.loops.Looper;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {
	Looper mEnabledLooper = new Looper();
	Looper mDisabledLooper = new Looper();

	private final SubsystemManager mSubsystemManager = SubsystemManager.getInstance();

	private Drive mDrive;
	private Arm mArm;
	private Belt mBelt;
	private Turret mTurret;
	private Piston mPiston;
	private Flywheel mFlywheel;
	private Vision mVision;
	
	private XboxController mXbox;


	@Override
	public void robotInit() {
		mDrive = Drive.getInstance();
		mArm = Arm.getInstance();
		mBelt = Belt.getInstance();
		mTurret = Turret.getInstance();
		mVision = Vision.getInstance();
		mPiston = Piston.getInstance();
		mFlywheel = Flywheel.getInstance();
		mSubsystemManager.setSubsystems(mDrive, mArm, mBelt, mTurret, mVision, mPiston, mFlywheel);

		mXbox = new XboxController(Constants.kDriveStickPort);

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

		mDrive.setCheesyishDrive(mXbox.getRawAxis(1), mXbox.getRawAxis(4), mXbox.getRawButton(1));
		mArm.setArm(mXbox.getAButton(), mXbox.getBButton());
		mBelt.setBelt(mXbox.getXButton(), mXbox.getYButton());
		if (mXbox.getPOV() != -1) {
			mTurret.setTurretAuto(mVision.x);
		} else {
			mTurret.setTurretManual(mXbox.getTriggerAxis(Hand.kLeft) - mXbox.getTriggerAxis(Hand.kRight));
		}
		mPiston.setPiston(mXbox.getRawButton(5));
		mFlywheel.setFlywheel(mXbox.getRawButton(6));

	}

}
