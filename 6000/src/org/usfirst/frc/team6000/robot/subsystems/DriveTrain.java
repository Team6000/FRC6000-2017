package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;
import org.usfirst.frc.team6000.robot.commands.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	RobotDrive robotDrive = new RobotDrive(0, 1);
	private AHRS gyro;
	double currentError = 0, previousError = 0, sumError = 0, slopeError = 0;
	

	public DriveTrain() {		
		gyro = Robot.ahrs;
		
		RobotMap.leftWheelEncoder.setMaxPeriod(0.1);
		RobotMap.leftWheelEncoder.setMinRate(10);
		RobotMap.leftWheelEncoder.setDistancePerPulse(18.85 / 360);
		RobotMap.leftWheelEncoder.setSamplesToAverage(50);

		RobotMap.rightWheelEncoder.setMaxPeriod(0.1);
		RobotMap.rightWheelEncoder.setMinRate(10);
		RobotMap.rightWheelEncoder.setDistancePerPulse(18.85 / 360);
		RobotMap.rightWheelEncoder.setSamplesToAverage(50);
	}

	public void setLeft(double speed) {
		RobotMap.leftMotor.set(speed);
	}

	public void setRight(double speed) {
		RobotMap.rightMotor.set(speed);
	}

	public void rawDrive(double left, double right) {
		RobotMap.rightMotor.set(right);
		RobotMap.leftMotor.set(left);
	}

	public void tankDrive(Joystick leftStick, Joystick rightStick) {
		robotDrive.tankDrive(leftStick, rightStick);
	}
	
	public void rotate(double angle) {
		
		previousError = currentError;
		currentError = angle - gyro.pidGet();
		sumError += currentError;
		slopeError = currentError - previousError;
		
		double kP = 0.04;
		double kI = 0.00001;
		double kD = 0;
		
		RobotMap.leftMotor.set(kP * currentError + kI * sumError + kD * slopeError);
		RobotMap.rightMotor.set(-(kP * currentError + kI * sumError + kD * slopeError));
		
	}
	
	public void pidDrive(Waypoint[] points) {
//		Waypoint[] points = new Waypoint[] { new Waypoint(-4, -1, Pathfinder.d2r(-45)),  
//				new Waypoint(-2, -2, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
//				new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
//		};

		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_FAST, 0.05, 4.27, 2.0, 60.0);
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier modifier = new TankModifier(trajectory).modify(0.68);
		Trajectory leftT = modifier.getLeftTrajectory(); // Get the Left Side
		Trajectory rightT = modifier.getRightTrajectory(); // Get the Right Side
		
		/*creates Encoder Followers. 
		To get your robot to follow a trajectory, you can use the EncoderFollower object. 
		As the name suggests, this will use encoders as feedback to guide your robot along the trajectory. 
		It is important that your time step passed into your Trajectory Configuration is the same as the time difference between control loop iterations, 
		otherwise you may find your path tracking inaccurately */
		

		EncoderFollower leftEncoderFollower = new EncoderFollower(leftT); 
		EncoderFollower rightEncoderFollower = new EncoderFollower(rightT);
		
		// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
		// 'getEncPosition' function. WE DONT KNOW WHAT THIS MEANS!!!!
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
		leftEncoderFollower.configureEncoder((int) RobotMap.leftWheelEncoder.getDistance(), 1440, .1524);
		rightEncoderFollower.configureEncoder((int) RobotMap.rightWheelEncoder.getDistance(), 1440, .1524);

		// The first argument is the proportional gain. Usually this will be quite high
		// The second argument is the integral gain. This is unused for motion profiling
		// The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
		// The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
        //trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
		// The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
		leftEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / 4.27, 0);
		rightEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / 4.27, 0);
		
		double leftOutput = leftEncoderFollower.calculate((int) RobotMap.leftWheelEncoder.getDistance()); 
		//Supposed to pass in current, cumulative position of encoder. DONT WHAT IT IS. using getDistance for right now
		double rightOutput = rightEncoderFollower.calculate((int) RobotMap.rightWheelEncoder.getDistance());
		//Supposed to pass in current, cumulative position of encoder. DONT WHAT IT IS. using getDistance for right now

		        double gyroHeading = (Double) gyro.getAngle();//FIND GYRO HEADING USING GYROSCOPE
		        // Assuming the gyro is giving a value in degrees
				double desiredHeading = Pathfinder.r2d(leftEncoderFollower.getHeading());  // Should also be in degrees
				double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
				double turn = 0.8 * (-1.0/80.0) * angleDifference;
		
				RobotMap.leftMotor.set(leftOutput - turn);
				RobotMap.rightMotor.set(rightOutput + turn);

		

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDrive());
	}
}
