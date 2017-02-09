package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class PathfinderTest extends Command{

    public PathfinderTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	
	    	/* Create an array of Waypoints. This array is passed to the pidDrive method
	    	 * in order to chart a route. The first two elements of a Waypoint are the x and y
	    	 * coordinates, and the last element is the exit angle (in radians). Use the .d2r
	    	 * command to convert degrees to radians (if you would like to input the exit angle
	    	 * in degrees). The Pathfinder program assumes that you begin on the first coordinate
	    	 * (so it can be whatever you want). 
	    	 */
	    	
	    	Waypoint[] points = new Waypoint[] { 
	    	new Waypoint(-4, -1, Pathfinder.d2r(-45)),  
			new Waypoint(-2, -2, 0), // Waypoint @ x=-2, y=-2, exit angle=0 radians
			new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
	    	};
			
	    	Robot.driveTrain.pidDrive(points);
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
