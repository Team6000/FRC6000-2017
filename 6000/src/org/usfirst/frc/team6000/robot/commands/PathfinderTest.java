package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.subsystems.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathfinderTest extends Command {

	EncoderFollower leftEncoderFollower;
	EncoderFollower rightEncoderFollower;
	private AHRS gyro;

	int position = 0;
	int gearPos = 0;
	double x1, x2, x3, y1, y2, y3, a1, a2, a3;

	public PathfinderTest(int pos, int gear) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		gyro = Robot.ahrs;
		position = pos;
		gearPos = gear;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	    	Robot.driveTrain.getLeftWheelEncoder().reset();
	    	Robot.driveTrain.getRightWheelEncoder().reset();
	    	gyro.reset();
	    	
	    	SmartDashboard.putNumber("x1", 0);
	    	SmartDashboard.putNumber("y1", 0);
	    	SmartDashboard.putNumber("a1", 0);
	    	
	    	SmartDashboard.putNumber("x2", 0);
	    	SmartDashboard.putNumber("y2", 0);
	    	SmartDashboard.putNumber("a2", 0);
	    	
	    	SmartDashboard.putNumber("x3", 0);
	    	SmartDashboard.putNumber("y3", 0);
	    	SmartDashboard.putNumber("a3", 0);
	    	
	    	x1 = SmartDashboard.getNumber("x1", 0);
	    	y1 = SmartDashboard.getNumber("y1", 0);
	    	a1 = SmartDashboard.getNumber("a1", 0);
	    	
	    	x2 = SmartDashboard.getNumber("x2", 0);
	    	y2 = SmartDashboard.getNumber("y2", 0);
	    	a2 = SmartDashboard.getNumber("a2", 0);
	    	
	    	x3 = SmartDashboard.getNumber("x3", 0);
	    	y3 = SmartDashboard.getNumber("y3", 0);
	    	a3 = SmartDashboard.getNumber("a3", 0);
	    	
	    	/* Create an array of Waypoints. This array is passed to the pidDrive method
	    	 * in order to chart a route. The first two elements of a Waypoint are the x and y
	    	 * coordinates, and the last element is the exit angle (in radians). Use the .d2r
	    	 * command to convert degrees to radians (if you would like to input the exit angle
	    	 * in degrees). The Pathfinder program assumes that you begin on the first coordinate
	    	 * (so it can be whatever you want). 
	    	 */
	    	Waypoint[] points = new Waypoint[] { 
			    	new Waypoint(x1, y1, a1),  
					new Waypoint(x2, y2, a2), // Waypoint @ x=-2, y=-2, exit angle=0 radians
					new Waypoint(x3, y3, a3) // Waypoint @ x=0, y=0, exit angle=0 radians
			    	};
	    	
	    	switch(position){
	    	case 1:
	    		switch(gearPos){
	    		case 1:
			    	points = new Waypoint[] { 
			    	new Waypoint(0, 0, 0),  
					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
			    	};
			    	break;
	    		case 2:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		case 3:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		default:
	    			break;
	    		}
	    	case 2:
	    		switch(gearPos){
	    		case 1:
			    	points = new Waypoint[] { 
			    	new Waypoint(0, 0, 0),  
					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
			    	};
			    	break;
	    		case 2:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		case 3:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		default:
	    			break;
	    		}
	    	case 3:
	    		switch(gearPos){
	    		case 1:
			    	points = new Waypoint[] { 
			    	new Waypoint(0, 0, 0),  
					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
			    	};
			    	break;
	    		case 2:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		case 3:
	    			points = new Waypoint[] { 
	    			    	new Waypoint(0, 0, 0),  
	    					new Waypoint(0, 0, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    					new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    			    	};
	    			break;
	    		default:
	    			break;
	    		}
    		default:
    			break;
	    	}
	    	
	    	// 1 Kody = 77 inches (kody and not codie because of Zev Kent)
	    	
			Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
					   Trajectory.Config.SAMPLES_FAST, 0.05, 4.7, 0.5, 60.0);
					Trajectory trajectory = Pathfinder.generate(points, config);
					TankModifier modifier = new TankModifier(trajectory).modify(0.68);
					Trajectory leftT = modifier.getLeftTrajectory(); // Get the Left Side
					Trajectory rightT = modifier.getRightTrajectory(); // Get the Right Side
					/*creates Encoder Followers. 
					To get your robot to follow a trajectory, you can use the EncoderFollower object. 
					As the name suggests, this will use encoders as feedback to guide your robot along the trajectory. 
					It is important that your time step passed into your Trajectory Configuration is the same as the time difference between control loop iterations, 
					otherwise you may find your path tracking inaccurately */
					

				  leftEncoderFollower = new EncoderFollower(leftT); 
					rightEncoderFollower = new EncoderFollower(rightT);
					
					// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
					// 'getEncPosition' function. WE DONT KNOW WHAT THIS MEANS!!!!
					// 1000 is the amount of encoder ticks per full revolution
					// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
					leftEncoderFollower.configureEncoder(Robot.driveTrain.getLeftWheelEncoder().get(), 1440, .1524);
					rightEncoderFollower.configureEncoder(Robot.driveTrain.getRightWheelEncoder().get(), 1440, .1524);

					// The first argument is the proportional gain. Usually this will be quite high
					// The second argument is the integral gain. This is unused for motion profiling
					// The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
					// The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
			        //trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
					// The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
					leftEncoderFollower.configurePIDVA(0.8, 0.0, 0.0, 1 / 4.27, 0);
					rightEncoderFollower.configurePIDVA(0.8, 0.0, 0.0, 1 / 4.27, 0);
					
//					for (int i = 0; i < trajectory.length(); i++) {
//					    Trajectory.Segment seg = trajectory.get(i);
//
//					    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
//					        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
//					            seg.acceleration, seg.jerk, seg.heading);
//					}
	    }

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Inside Execute Loop");
		double leftOutput = leftEncoderFollower.calculate(Robot.driveTrain.getRightWheelEncoder().get());
		// Supposed to pass in current, cumulative position of encoder. DONT
		// WHAT IT IS. using getDistance for right now
		double rightOutput = rightEncoderFollower.calculate(Robot.driveTrain.getRightWheelEncoder().get());
		// Supposed to pass in current, cumulative position of encoder. DONT
		// WHAT IT IS. using getDistance for right now
		double gyroHeading = gyro.getAngle();// FIND GYRO HEADING USING
												// GYROSCOPE
		SmartDashboard.putNumber("getAngle", gyroHeading);
		// SmartDashboard.putNumber("pidGet", gyro.pidGet());
		// Assuming the gyro is giving a value in degrees
		double desiredHeading = Pathfinder.r2d(leftEncoderFollower.getHeading()); // Should
																					// also
																					// be
																					// in
																					// degrees
		double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
		double turn = 3 * (-1.0 / 80.0) * angleDifference;
		SmartDashboard.putNumber("Turn", turn);

		// while (Math.abs(Math.abs(leftOutput) - Math.abs(rightOutput)) >=
		// 0.0001)
		// {
		// rightOutput += 0.0001;
		// }

		Robot.driveTrain.rawDrive(leftOutput + turn, -rightOutput + turn);

		// System.out.println("getAngle: " + gyroHeading);
		// System.out.println("angleDifference: " + angleDifference);
		// System.out.println("RightWheelEncoder.get(): " +
		// Robot.driveTrain.getRightWheelEncoder().get());
		// System.out.println("LeftWheelEncoder.get(): " +
		// Robot.driveTrain.getLeftWheelEncoder().get());
		// System.out.println("turn: " + turn);
		System.out.println("leftOutput: " + leftOutput);
		System.out.println("rightOutput: " + rightOutput);
		SmartDashboard.putNumber("right Encoder", Robot.driveTrain.getRightWheelEncoder().getDistance());
		SmartDashboard.putNumber("left Encoder", Robot.driveTrain.getLeftWheelEncoder().getDistance());
		SmartDashboard.putNumber("left value", leftOutput);
		SmartDashboard.putNumber("right value", rightOutput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
