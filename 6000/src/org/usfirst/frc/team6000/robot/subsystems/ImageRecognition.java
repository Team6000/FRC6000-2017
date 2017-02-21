package org.usfirst.frc.team6000.robot.subsystems;

//import com.ni.vision.NIVision.Point;
import org.usfirst.frc.team6000.robot.subsystems.CPoint;
import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ImageRecognition extends Subsystem {

	double tapeHeight = 0;
	double distance = 0;
	double angAlign = 0;
	double centerToTape = 0;

	static double RADIUS = 13;
	static double SCREEN_WIDTH = 640;
	static double VERTICAL_VIEW_ANGLE = 19.02;

	protected void initDefaultCommand() {
		
	}
	
	// Loads Camera Data and sets variables for the dimensions.
	public void loadCameraData() {
//		CameraData camera = new CameraData();
		
		if (!Robot.cmData.pointedAtGrip()) return;
		
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
		
		CPoint P2,P4,P5,P7;
//		P1 = Robot.cmData.getP1();
		P2 = Robot.cmData.getP2();
//		P3 = Robot.cmData.getP3();
		P4 = Robot.cmData.getP4();
		P5 = Robot.cmData.getP5();
//		P6 = Robot.cmData.getP6();
		P7 = Robot.cmData.getP7();
//		P8 = Robot.cmData.getP8();
		
		//center is the center of all the tape
		CPoint center = new CPoint();
		
		center.x = (P2.x+P5.x)/2;
		center.y = (P2.y+P4.y)/2;
		
		//screenCenter is center of screen
		CPoint screenCenter = new CPoint();
		screenCenter.x = 320;
		screenCenter.y = 240;
		
		centerToTape = Robot.cmData.findDistance(center,screenCenter); 
		tapeHeight = ((P4.y - P2.y) + (P7.x - P5.y))/2;
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
		
		dis = (240)/(Math.tan(Math.toRadians(VERTICAL_VIEW_ANGLE)));
		dis = convertToInches(dis);
		
		return dis;
	}
	
	// converts a length in the image from pixels to inches
	public double convertToInches (double pix){
		double ratio = 0;
		ratio = 5/tapeHeight;
		
		return pix * ratio;
	}
	// converts a length in the image from inches to pixels
	public double convertToPixels (double inches){
		double ratio = 0;
		ratio = tapeHeight/5;
		
		return inches * ratio;
	}

}
