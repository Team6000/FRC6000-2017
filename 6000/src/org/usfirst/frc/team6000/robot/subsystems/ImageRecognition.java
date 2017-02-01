package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ImageRecognition extends Subsystem {

	double tapeHeight = 0;
	double distance = 0;
	double angAlign = 0;
	
	protected void initDefaultCommand() {
		
	}
	
	// For autonomous
	
	// Checks if the camera is looking at the vision targets in the gear peg area
	public void checkIfGearTarget() {
		
	}
	
	/*
	  Aligns robot to have to look at the center of tapes.  Essentially you should be staring right at the peg.
	  angAlign is alpha in the diagram.
	  w is the pixel distance from the center of the image to the center of the tapes.
	  d is the width of the image in pixels.
	  
	  agnAlign = arctan((w/d)tan(30));
	*/
	
	public void alignCenter() {
		
	}
	
	/* Checks if the robot is at a good enough angle to move straight to put the gear on the peg
	   Requires: Ultrasonic sensor data
	   Variables in use:
	     d = distance from camera to wall that bisects the viewing angle of the camera
	     w = half the width in the camcera frame
	     angRaw = the angle between the viewing angle bisector and the flat wall
	     angError = the difference between 90 degrees, which is perfect, and the angRaw
	   To calculate angRaw:
	     90 degrees : 2in. just as angRaw : apparent width of retroflective tape
	   To calculate Apparent width of retroflective tape
	     (FIND FORMULA TO CALCULATE W WHEN ANGRAW IS 90 DEGREES) inches : w in pixels just as apparent width : w in pixels.
	*/
	public void checkIfRightAngle(float d) {
		double w = 0; // in Pixels originally, then convert to inches
		double angRaw = 90.0;
		double angError = 90 - angRaw;
		double maxError = 0;
	}
	
	// converts a length in the image from pixels to inches
	public void convertToInches (float pix){
		// return pixels divided by DPI (dots per inch)
	}

}

