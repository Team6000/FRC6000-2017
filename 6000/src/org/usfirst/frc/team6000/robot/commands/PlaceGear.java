
public class PlaceGear {

	public class Shooter extends Command {
	
		// Called just before this Command runs the first time
	    protected void initialize() {

	    }
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
