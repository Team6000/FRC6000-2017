package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class PlaceGear extends Command{

	public double alignAngle = 0;
	public double disToTarget = 0;
	
	public PlaceGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		//requires(Robot.shooter);
//		requires(Robot.driveTrain);
		requires(Robot.imgRec);
    }

		// Called just before this Command runs the first time
	    protected void initialize() {

	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	/* Steps:
	    	 * 
	    	 * Check distance
	    	 * - If too far move forward
	    	 * - If at optimal distance and aligned place gear
	    	 * 
	    	 */
	    	alignAngle = Robot.imgRec.alignCenter();
	    	
	    	// rotate the robot to have it facing the center of the tapes
//	    	Robot.driveTrain.rotate(alignAngle);
	    	// Get the distance to travel with imgRec
	    	// distanceToTarget returns a value in INCHES
	    	// Drive forward to place gear, wait for a few seconds, then drive back
	    	disToTarget = Robot.imgRec.distanceToTarget();
	    	
	    	
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
