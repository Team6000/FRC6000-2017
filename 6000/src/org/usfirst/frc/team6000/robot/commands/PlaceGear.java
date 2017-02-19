package org.usfirst.frc.team6000.robot.commands;

import org.opencv.core.Mat;
import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;



public class PlaceGear extends Command{

	public double alignAngle = 0;
	public double disToTarget = 0;
	Mat source = new Mat();
	
	
	
	public PlaceGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		//requires(Robot.shooter);
		requires(Robot.driveTrain);
		requires(Robot.imgRec);
    }

	
	
		// Called just before this Command runs the first time
	    protected void initialize() {
	    	System.out.println("Running PlaceGear");

	    	new Thread(() -> {
                while(!Thread.interrupted()) {
                	Robot.cvSink.grabFrame(source);
        			Robot.pipeline.process(source);
                    Robot.imgOutput.putFrame(Robot.pipeline.hsvThresholdOutput());
                    System.out.println("Running the thread.  KEEP TRACK OF THIS SHIT");
                }
            }).start();
			
			if(Robot.pipeline.findLinesOutput().size() != 0){
				System.out.println(Robot.pipeline.filterLinesOutput.get(0));
			}
			else{
				System.out.println("ERROR: Lines output is 0.  SHIT!!!");
			}
			
	    	alignAngle = Robot.imgRec.alignCenter();
	    	disToTarget = Robot.imgRec.distanceToTarget();
//	    	Robot.driveTrain.rotate(alignAngle);
	    	// driveTrain.rotate should call the method to drive forward after it runs fully
	    	System.out.println(alignAngle + " , " + disToTarget);
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	// Check if the whole process is done and then call isFinished().
	    	this.isFinished();
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	        return true;
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	
	    }
		
}
