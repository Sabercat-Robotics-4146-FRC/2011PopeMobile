package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Controller {

  public static final int leftXAxis = 0;
  public static final int leftYAxis = 1;
  public static final int leftTrigger = 2;
  public static final int rightTrigger = 3;
  public static final int rightXAxis = 4;
  public static final int rightYAxis = 5;

  /**
     * used by getDeadband functions. Values within the distance of this value from zero will be set to 0
     * 
     * @see #getDeadbandLeftXAxis()
     * @see #getDeadbandLeftYAxis()
     * @see #getDeadbandRightXAxis()
     * @see #getDeadbandRightYAxis()
     */
    public static final double CONTROLLER_DEADBAND  = 0.15;
}

public class Robot extends SampleRobot {

  WPI_TalonSRX leftDrive = new WPI_TalonSRX(1); //1 - 4 to account for future errors in the CAN Network
  WPI_TalonSRX rightDrive = new WPI_TalonSRX(2);
  WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(3);
  WPI_TalonSRX rightDriveBack = new WPI_TalonSRX(4);

  //PR Robot Talons
  // WPI_TalonSRX leftDrive = new WPI_TalonSRX(1); //1 - 4 to account for future errors in the CAN Network
  // WPI_TalonSRX rightDrive = new WPI_TalonSRX(2);
  // WPI_TalonSRX leftDriveBack = new WPI_TalonSRX(3);
  // WPI_TalonSRX rightDriveBack = new WPI_TalonSRX(4);

  SpeedControllerGroup leftDriveGroup = new SpeedControllerGroup(leftDrive, leftDriveBack);
  SpeedControllerGroup rightDriveGroup = new SpeedControllerGroup(rightDrive, rightDriveBack);

  private final DifferentialDrive drive = new DifferentialDrive(leftDriveGroup, rightDriveGroup);
  private final Joystick joystick = new Joystick(0);


   /**
     * used by getDeadband functions. Values within the distance of this value from zero will be set to 0
     * 
     * @see #getDeadbandLeftXAxis()
     * @see #getDeadbandLeftYAxis()
     * @see #getDeadbandRightXAxis()
     * @see #getDeadbandRightYAxis()
     */
    
    public static final double CONTROLLER_DEADBAND  = 0.15;
    
  public Robot() {}

  @Override

  public void robotInit() {}

  @Override

  public void autonomous() {}

  @Override

 public void joystick() {

    exampleStick = new Joystick(1);
 }
    
    public double getLeftXAxis() {
      return joy.getRawAxis(leftXAxis);
    }
    
    /**
     * Returns the value of the y axis of the left stick. (-1.0 to 1.0, 0.0 is centered)
     * Value is inverted to make it so forward is positive because the default is backwards is positive
     * 
     * @return a double of the y axis position for the left stick
     */

    public double getLeftYAxis() {
      return -(joy.getRawAxis(leftYAxis));
    }
    
    /**
     * Returns the value of the x axis of the right stick. (-1.0 to 1.0, 0.0 is centered)
     * 
     * @return a double of the x axis position for the right stick
     */

    public double getRightXAxis() {
      return joy.getRawAxis(rightXAxis);
    }
    
    /**
     * Returns the value of the y axis of the right stick. (-1.0 to 1.0, 0.0 is centered)
     * Value is inverted to make it so forward is positive because the default is backwards is positive
     * 
     * @return a double of the y axis position for the right stick
     */

    public double getRightYAxis() {
      return -(joy.getRawAxis(rightYAxis));
    }
    
    //Deadbanded Axes:
    
    /**
     * Returns the value of the x axis of the left stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
     * ctrlDeadband is applied. Meaning -{@link #ctrlDeadband} to {@link #ctrlDeadband} values are set to 0
     * 
     * @return a double from the result of {@link #joystickDeadband(double)} for the x axis position of the left stick
     */

    public double getDeadbandLeftXAxis() {
      return joystickDeadband(joy.getRawAxis(leftXAxis));
    }
    
    /**
     * Returns the value of the y axis of the left stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
     * Value is inverted to make it so forward is positive because the default is backwards is positive.
     * ctrlDeadband is applied. Meaning -{@link #ctrlDeadband} to {@link #ctrlDeadband} values are set to 0
     * 
     * @return a double from the result of {@link #joystickD eadband(double)} for the y axis position of the left stick
     */

    public double getDeadbandLeftYAxis() {
      return joystickDeadband(-(joy.getRawAxis(leftYAxis)));
    }
    
    /**
     * Returns the value of the x axis of the right stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
     * ctrlDeadband is applied. Meaning -{@link #ctrlDeadband} to {@link #ctrlDeadband} values are set to 0
     * 
     * @return a double from the result of {@link #joystickDeadband(double)} for the x axis position of the right stick
     */

    public double getDeadbandRightXAxis() {
      return joystickDeadband(joy.getRawAxis(rightXAxis));
    }
    
    /**
     * Returns the value of the y axis of the right stick with a deadband. (-1.0 to 1.0, 0.0 is centered)
     * Value is inverted to make it so forward is positive because the default is backwards is positive.
     * ctrlDeadband is applied. Meaning -{@link #ctrlDeadband} to {@link #ctrlDeadband} values are set to 0
     * 
     * @return a double from the result of {@link #joystickDeadband(double)} for the y axis position of the right stick
     */

    public double getDeadbandRightYAxis() {
      return joystickDeadband(-(joy.getRawAxis(rightYAxis)));
    }
    
    /**
     * Takes the value of a joystick axis and returns it deadbanded. (-1.0 to 1.0, 0.0 is centered)
     * If value is between -{@link #ctrlDeadband} and {@link #ctrlDeadband} then value is set to 0.0
     * 
     * @param joystickInput the value given to the function to be deadbanded
     * @return the input deadbanded.
     */


    private double joystickDeadband(double joystickInput) {
      if(Math.abs(joystickInput) < CONTROLLER_DEADBAND) { 
        //Was (joystickInput < CONTROLLER_DEADBAND) && (joystickInput > -CONTROLLER_DEADBAND)
        return (double)(0.0);//does this have to be cast?
      } else {
        return joystickInput;
      }
    }

  public void operatorControl() {

    while (isOperatorControl() && isEnabled()) {
      drive.arcadeDrive(-joystick.getY(), joystick.getX());
      Timer.delay(0.005);
    }
  }

// public class flyWheel {

//   while (isOperatorControl() && isEnabled()) {
    
//   }
// }  
}