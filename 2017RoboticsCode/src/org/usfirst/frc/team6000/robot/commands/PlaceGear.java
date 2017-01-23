package org.usfirst.frc.team6000.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PlaceGear {

	public class Shooter extends Command {
	
		// Called just before this Command runs the first time
	    protected void initialize() {

	    }

		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected void end() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void interrupted() {
			// TODO Auto-generated method stub
			
		}

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	/* Steps:
	    	 * 
	    	 * Check distance
	    	 * - If too far move forward
	    	 * - If at optimal distance and aligned place gear
	    	 * Check alignment
	    	 * - Adjust alignment as necessary or display in dashboard for driver
	    	 * 
	    	 */
	    }
	}		
}
