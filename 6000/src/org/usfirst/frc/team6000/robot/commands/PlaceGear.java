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
//		requires(Robot.imgRec);
    }

	
	
		// Called just before this Command runs the first time
	    protected void initialize() {
	    	System.out.println("Place Gear");
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	// Check if the whole process is done and then call isFinished().
	    	// Makes the code only run once from the initialize() method
	    	Robot.driveTrain.gyroReset();
	    	Robot.driveTrain.rotate(90.0);
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
