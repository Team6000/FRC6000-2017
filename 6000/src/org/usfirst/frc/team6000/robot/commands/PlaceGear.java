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
	
	public PlaceGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		//requires(Robot.shooter);
		requires(Robot.driveTrain);
		requires(Robot.imgRec);
    }

	
	
		// Called just before this Command runs the first time
	    protected void initialize() {
	    	alignAngle = 0;
	    	Robot.cvSink = CameraServer.getInstance().getVideo();
	    	System.out.println("Running PlaceGear");
//	    	System.out.println(Robot.source);
	    	Robot.cvSink.grabFrame(Robot.source);
//	    	System.out.println(Robot.source);
	    	Robot.pipeline.process(Robot.source);
//	    	System.out.println(Robot.pipeline.hsvThresholdOutput());
	    	
			// Fail Safe:
	    	// If PlaceGear is accidentally ran or the image is not picking up any lines, process will stop here
			if(Robot.pipeline.findLinesOutput().size() != 0){
				System.out.println("LinesSize: " + Robot.pipeline.findLinesOutput().size());
				Robot.cmData.doProcess();
				Robot.imgRec.loadCameraData();
				
		    	alignAngle = Robot.imgRec.alignCenter();
		    	SmartDashboard.putNumber("AlignAngle", alignAngle);
		    	disToTarget = Robot.imgRec.distanceToTarget();
//		    	Robot.driveTrain.rotate(alignAngle);
		    	// driveTrain.rotate should call the method to drive forward after it runs fully
		    	System.out.println(alignAngle);
		    	System.out.println(disToTarget);
			}
			else{
				System.out.println("ERROR: Lines output is 0");
			}
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	// Check if the whole process is done and then call isFinished().
	    	// Makes the code only run once from the initialize() method
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
