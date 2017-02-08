package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ImageRecognition extends Subsystem {

	double tapeHeight = 0;
	double distance = 0;
	double angAlign = 0;
	double radius = 0;
	double centerToTape = 0;
	double screenWidth = 0;
	
	protected void initDefaultCommand() {
		
	}
	
	// For autonomous
	//find distance between two Points
	private double findDistance(Point pt1, Point pt2){
		double x1,x2,y1,y2;
		x1 = pt1.getX();
		y1 = pt1.getY();
		x2 = pt2.getX();
		y2 = pt2.getY();
		
		return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
	}
	
	// Loads Camera Data and sets variables for the dimensions.
	public void loadCameraData() {
		CameraData camera = new CameraData();
		
		if (!camera.pointedAtGrip()) return;
		
		//TL = Top left
		//BR = Bottom Right 
		//etc...
		Point TL,TR,BL,BR;
		TL = camera.getTopLeft();
		TR = camera.getTopRight();
		BL = camera.getBottomLeft();
		BR = camera.getBottomRight();

		//center is the center of all the tape
		Point center = new Point();
		center.setLocation( (TL.getX()+TR.getX()+BL.getX()+BR.getX() ) / 4, (TL.getY()+TR.getY()+BL.getY()+BR.getY() ) / 4);
		
		//screenCenter is center of screen
		Point screenCenter = new Point();
		screenCenter.setLocation(camera.CAM_WIDTH()/2,camera.CAM_WIDTH()/2);
		
		this.centerToTape = findDistance(center,screenCenter); 
		this.tapeHeight = (TL.getY() + TR.getY())/2 - (BL.getY() + BR.getY())/2;
		this.tapeWidth = (TR.getX() + BR.getX())/2 - (TL.getX() + BL.getX())/2;

	}

	
	/*
	  Aligns robot to have to look at the center of tapes.  Essentially you should be staring right at the peg.
	  angAlign is alpha in the diagram.
	  centerToTape is the pixel distance from the center of the image to the center of the tapes.
	  screenWidth is the width of the image in pixels.
	  30 is half the horizontal viewing angle
	  radius is the distance from the center of the robot to the 
	*/

	public double alignCenter() {
		radius = convertToPixels(radius);
		angAlign = Math.atan((centerToTape*(Math.tan(30)))/((screenWidth/2)+radius*(Math.tan(30))));
		
		return angAlign;
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
	public double convertToInches (double pix){
		double ratio = 0;
		ratio = tapeHeight/5;
		
		return pix * ratio;
	}
	public double convertToPixels (double inches){
		double ratio = 0;
		ratio = 5/tapeHeight;
		
		return inches * ratio;
	}

}
