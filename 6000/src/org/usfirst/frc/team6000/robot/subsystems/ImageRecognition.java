package org.usfirst.frc.team6000.robot.subsystems;

//import com.ni.vision.NIVision.Point;
import java.awt.Point;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.awt.Point;

public class ImageRecognition extends Subsystem {

	double tapeHeight = 0;
	double distance = 0;
	double angAlign = 0;
	double centerToTape = 0;

	// MEASURE DISTANCE FROM CENTER OF ROBOT TO CAMERA AND CUTOFF DISTANCE
	static double RADIUS = 0;
	static double SCREEN_WIDTH = 1280;
	static double VERTICAL_VIEW_ANGLE = 19.02;

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
		
		/*
		 *       p1---------p2          p5---------p6
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |          |           |			|
		 *       |          |           |			|
		 *       |          |           |			|
		 *       |          |           |			|
		 *       |          |           |			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       |			|			|			|
		 *       p3---------p4          p7---------p8
		 * 
		 */
		
		// Run the entire process of CameraData to get P1-P8
		Robot.cmData.entireProcess();
		
		Point P1,P2,P3,P4,P5,P6,P7,P8;
		P1 = camera.getP1();
		P2 = camera.getP2();
		P3 = camera.getP3();
		P4 = camera.getP4();
		P5 = camera.getP5();
		P6 = camera.getP6();
		P7 = camera.getP7();
		P8 = camera.getP8();
		
		//center is the center of all the tape
		Point center = new Point();
		center.setLocation(((P2.getX()+P5.getX())/2),((P2.getY()+P4.getY()/2)));
		
		//screenCenter is center of screen
		Point screenCenter = new Point();
		screenCenter.setLocation(camera.CAM_WIDTH()/2,camera.CAM_WIDTH()/2);
		
		this.centerToTape = findDistance(center,screenCenter); 
		this.tapeHeight = (P2.getY() - P4.getY() + P5.getY() - P7.getY())/2;
		//this.tapeWidth = (P6.getX() + P6.getX())/2 - (P7.getX() + P8.getX())/2;

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
		RADIUS = convertToPixels(RADIUS);
		angAlign = Math.atan((centerToTape*(Math.tan(Math.toRadians(30))))/((SCREEN_WIDTH/2)+RADIUS*(Math.tan(Math.toRadians(30)))));
		angAlign = Math.toDegrees(angAlign);
		return angAlign;
	}
	
	/*
	 * Uses the ratio of the tapeHeight and inches to get the distance from the camera to the target in pixels
	 * Converts the distance in pixels to inches using the ratio from the tapeHeight
	 * 360 is half the vertical pixels of the 720p camera
	 */
	
	public double distanceToTarget() {
		double dis = 0;
		
		dis = (360)/Math.tan(Math.toRadians(VERTICAL_VIEW_ANGLE));
		dis = convertToInches(dis);
		
		return dis;
	}
	
	// converts a length in the image from pixels to inches
	public double convertToInches (double pix){
		double ratio = 0;
		ratio = tapeHeight/5;
		
		return pix * ratio;
	}
	// converts a length in the image from inches to pixels
	public double convertToPixels (double inches){
		double ratio = 0;
		ratio = 5/tapeHeight;
		
		return inches * ratio;
	}

}
