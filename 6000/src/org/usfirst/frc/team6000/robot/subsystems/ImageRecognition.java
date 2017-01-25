package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ImageRecognition extends Subsystem {

	protected void initDefaultCommand() {
		
	}
	
	// For autonomous
	
	// Checks if the camera is looking at the vision targets in the gear peg area
	public void checkIfGearTarget() {
		
	}
	
	/* Checks if the robot is at a good enough angle to move straight to put the gear on the peg
	   Requires: Ultrasonic sensor data
	   Variables in use:
	     d = distance from camera to wall that bisects the viewing angle of the camera
	     w = half the width in the camcera frame
	     angRaw = the angle between the viewing angle bisector and the flat wall
	     angError = the difference between 90 degrees, which is perfect, and the angRaw
	   To calculate angRaw:
	     90:angRaw just as 2in:apparent width of retroflective tape
	*/
	public void checkIfRightAngle(float d) {
		float w = 0; // in Pixels originally, then convert to inches
		float angRaw = 90.0;
		float angError = 90 - angRaw;
		float maxError = 0;
	}
	
	// converts a length in the image from pixels to inches
	public void convertToInches (float pix){
		
	}

}

