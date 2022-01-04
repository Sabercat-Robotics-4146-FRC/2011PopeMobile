/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.loops.Looper;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake2;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Turret;
//import frc.robot.subsystems.Piston;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Limelight;

public class Robot extends TimedRobot {
	Looper mEnabledLooper = new Looper();
	Looper mDisabledLooper = new Looper();

	private final SubsystemManager mSubsystemManager = SubsystemManager.getInstance();
	
	private Drive mDrive;

	private Intake2 mIntake; 
	//Taking Intake2 and putting into a manipulatable object for robotInit
	
	private Belt mBelt;

	private Turret mTurret;

    //private Piston mPiston;

	private Flywheel mFlywheel;

	private Joystick mDriveStick;

	private Limelight mLimelight;

	@Override
	public void robotInit() { //Initializes what is being used in the robot code
		mDrive = Drive.getInstance();
		mSubsystemManager.setSubsystems(mDrive);
		
		mIntake = Intake2.getInstance();
		mSubsystemManager.setSubsystems(mIntake);
		//Initialize an instance of intake in Robot
		
		mBelt = Belt.getInstance();
		mSubsystemManager.setSubsystems(mBelt);
		
		mTurret = Turret.getInstance();
		mSubsystemManager.setSubsystems(mTurret);
		
		//mPiston = Piston.getInstance();
		//mSubsystemManager.setSubsystems(mPiston);

		mFlywheel = Flywheel.getInstance();
		mSubsystemManager.setSubsystems(mFlywheel);

		mLimelight = Limelight.getInstance();
		mSubsystemManager.setSubsystems(mLimelight);

		mDriveStick = new Joystick(Constants.kDriveStickPort);

		mSubsystemManager.registerEnabledLoops(mEnabledLooper);
		mSubsystemManager.registerDisabledLoops(mDisabledLooper);
	}

	@Override
	public void robotPeriodic() {
		mSubsystemManager.outputToSmartDashboard();
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
		mIntake.setArmPosition(mDriveStick.getRawButton(5), mDriveStick.getRawButton(6));
		mBelt.setBeltDirection(mDriveStick.getRawButton(3), mDriveStick.getRawButton(2));
		mTurret.setTurretRotation(mDriveStick.getRawButton(7), mDriveStick.getRawButton(8));
		//mPiston.togglePiston(mDriveStick.getRawButton(4));
		mFlywheel.setFlywheelSpinning(mDriveStick.getRawButton(1));
		mLimelight.trackLimelight(true);
		SmartDashboard.putBoolean("XButton", mDriveStick.getRawButton(3));
		SmartDashboard.putBoolean("YButton", mDriveStick.getRawButton(4));
		SmartDashboard.putBoolean("AButton", mDriveStick.getRawButton(1));
	}
}
