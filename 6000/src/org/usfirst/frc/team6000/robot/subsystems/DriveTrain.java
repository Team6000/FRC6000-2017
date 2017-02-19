

package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;
import org.usfirst.frc.team6000.robot.commands.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private Spark leftDrive = new Spark(RobotMap.leftDrivePort);
	private Spark rightDrive = new Spark(RobotMap.rightDrivePort);
	
	private RobotDrive mainDrive = new RobotDrive(leftDrive, rightDrive);
	private AHRS gyro;
	double currentError = 0, previousError = 0, sumError = 0, slopeError = 0;
	
	private Encoder leftWheelEncoder = new Encoder(RobotMap.leftEncoderPortA, 
			RobotMap.leftEncoderPortB);
	private Encoder rightWheelEncoder = new Encoder(RobotMap.rightEncoderPortA, 
			RobotMap.rightEncoderPortB);	
	
	public DriveTrain() {		
		gyro = Robot.ahrs; 
		
		// THIS IS MESSED UP
		
		leftWheelEncoder.setMaxPeriod(0.1);
		leftWheelEncoder.setMinRate(10);
		leftWheelEncoder.setDistancePerPulse(18.85 / 1440);
		leftWheelEncoder.setReverseDirection(true);
		leftWheelEncoder.setSamplesToAverage(10);

		rightWheelEncoder.setMaxPeriod(0.1);
		rightWheelEncoder.setMinRate(10);
		rightWheelEncoder.setDistancePerPulse(18.85 / 1440);
		rightWheelEncoder.setReverseDirection(true);
		rightWheelEncoder.setSamplesToAverage(10);
	}

	public Encoder getRightWheelEncoder(){
		return rightWheelEncoder;
	}
	
	public Encoder getLeftWheelEncoder(){
		return leftWheelEncoder;
	}
	
	public void setLeft(double speed) {
		leftDrive.set(speed);
	}

	public void setRight(double speed) {
		rightDrive.set(speed);
	}

	public void rawDrive(double left, double right) {
		rightDrive.set(right);
		leftDrive.set(left);
	}

	public void tankDrive(Joystick leftStick, Joystick rightStick) {
		mainDrive.tankDrive(leftStick, rightStick);
	}
	
	public void rotate(double angle) {
		
		previousError = currentError;
		currentError = angle - gyro.pidGet();
		sumError += currentError;
		slopeError = currentError - previousError;
		
		double kP = 0.04;
		double kI = 0.00001;
		double kD = 0;
		
		leftDrive.set(kP * currentError + kI * sumError + kD * slopeError);
		rightDrive.set(-(kP * currentError + kI * sumError + kD * slopeError));
		
	}
	
	public void pidDrive(double leftOutput, Trajectory rightOutput) {
//		Waypoint[] points = new Waypoint[] { new Waypoint(-4, -1, Pathfinder.d2r(-45)),  
//				new Waypoint(-2, -2, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
//				new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
//		};
//
//		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
//		   Trajectory.Config.SAMPLES_FAST, 0.05, 3.07, 2.0, 60.0);
//		Trajectory trajectory = Pathfinder.generate(points, config);
//		TankModifier modifier = new TankModifier(trajectory).modify(0.68);
//		Trajectory leftT = modifier.getLeftTrajectory(); // Get the Left Side
//		Trajectory rightT = modifier.getRightTrajectory(); // Get the Right Side
//		
//		/*creates Encoder Followers. 
//		To get your robot to follow a trajectory, you can use the EncoderFollower object. 
//		As the name suggests, this will use encoders as feedback to guide your robot along the trajectory. 
//		It is important that your time step passed into your Trajectory Configuration is the same as the time difference between control loop iterations, 
//		otherwise you may find your path tracking inaccurately */
//		
//
//		EncoderFollower leftEncoderFollower = new EncoderFollower(leftT); 
//		EncoderFollower rightEncoderFollower = new EncoderFollower(rightT);
//		
//		// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
//		// 'getEncPosition' function. WE DONT KNOW WHAT THIS MEANS!!!!
//		// 1000 is the amount of encoder ticks per full revolution
//		// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
//		leftEncoderFollower.configureEncoder(leftWheelEncoder.getRaw(), 1440, .1524);
//		rightEncoderFollower.configureEncoder( rightWheelEncoder.getRaw(), 1440, .1524);
//
//		// The first argument is the proportional gain. Usually this will be quite high
//		// The second argument is the integral gain. This is unused for motion profiling
//		// The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
//		// The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
//        //trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
//		// The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
//		leftEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / 4.27, 0);
//		rightEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / 4.27, 0);
//		
//		double leftOutput = leftEncoderFollower.calculate(leftWheelEncoder.getRaw()); 
//		//Supposed to pass in current, cumulative position of encoder. DONT WHAT IT IS. using getDistance for right now
//		double rightOutput = rightEncoderFollower.calculate(rightWheelEncoder.getRaw());
//		//Supposed to pass in current, cumulative position of encoder. DONT WHAT IT IS. using getDistance for right now
//
//		        double gyroHeading = gyro.pidGet();//FIND GYRO HEADING USING GYROSCOPE
//		        // Assuming the gyro is giving a value in degrees
//				double desiredHeading = Pathfinder.r2d(leftEncoderFollower.getHeading());  // Should also be in degrees
//				double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
//				double turn = 0.8 * (-1.0/80.0) * angleDifference;
//				
//				double turn = 0.0;
//		
//				leftDrive.set(leftOutput + turn);
//				rightDrive.set(rightOutput - turn);
//
//		

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDrive());
	}
}
