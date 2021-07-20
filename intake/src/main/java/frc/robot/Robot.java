/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.loops.Looper;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.IntakeSubsystem;

public class Robot extends TimedRobot {
	Looper mEnabledLooper = new Looper();
	Looper mDisabledLooper = new Looper();

	private final SubsystemManager mSubsystemManager = SubsystemManager.getInstance();

	private Drive mDrive;

	private IntakeSubsystem mIntake;

	private Belt mBelt;
	
	private Joystick mDriveStick;

	private Joystick mController;

	public static boolean xbottomflag = false;
	public static boolean xbottom = false;

	public static boolean bButtonFlag = false;
	public static boolean bButton = false;

	

	@Override
	public void robotInit() {
		mDrive = Drive.getInstance();
		mIntake = IntakeSubsystem.getInstance();
		mBelt = Belt.getInstance();

		mSubsystemManager.setSubsystems(mDrive);
		mSubsystemManager.setSubsystems(mIntake);
		mSubsystemManager.setSubsystems(mBelt);


		mDriveStick = new Joystick(Constants.kDriveStickPort);

		mController = new Joystick(Constants.kDriveStickPort);
		

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

		if (!xbottomflag && mController.getRawButton(3)) {
			xbottom = !xbottom;
			xbottomflag = true;
		} else if (xbottomflag && !mController.getRawButton(3)) {
			xbottomflag = false;
		}
		if(!bButtonFlag && mController.getRawButton(2)){
			bButton = !bButton;
			bButtonFlag = true;
		}
		else if(bButtonFlag && !mController.getRawButton(2)){
			bButtonFlag = false;	
		}
		
		//mDrive.setCheesyishDrive(mThrottleStick.getRawAxis(1), -mTurnStick.getRawAxis(0), mTurnStick.getRawButton(1));
		mSubsystemManager.outputToSmartDashboard();
		mDrive.setCheesyishDrive(mDriveStick.getRawAxis(1), -mDriveStick.getRawAxis(4), mDriveStick.getRawButton(1));
		mIntake.armMovement(mController.getRawButton(5), mController.getRawButton(6));
		mBelt.beltRotation(xbottom, bButton);
	}
}
