package org.usfirst.frc.team6000.robot.subsystems;

//import com.ni.vision.NIVision.Point;
//import org.usfirst.frc.team6000.robot.subsystems.CPoint;
//import org.usfirst.frc.team6000.robot.Robot;

//import java.awt.Canvas;
//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.List;
//import java.awt.Rectangle;
//import java.awt.Shape;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.awt.image.ImageObserver;
//import java.awt.image.WritableRaster;
//import java.lang.reflect.Array;
//import java.text.AttributedCharacterIterator;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Timer;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
//import org.opencv.core.MatOfPoint;
//import org.opencv.core.Point;
import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImageRecognition extends Subsystem {

//	Process for GRIP	
	static LiftTracker tracker;
	public static VideoCapture videoCapture;
//	Constants for known variables
	static Mat matOriginal;
	public static final double OFFSET_TO_FRONT = 0;
	public static final double CAMERA_WIDTH = 640;
	public static final double DISTANCE_CONSTANT= 5745.6;
	public static final double WIDTH_BETWEEN_TARGET = 9.25;
	public static boolean shouldRun = true;
	static NetworkTable table;
	
	
	static double lengthBetweenContours;
	static double distanceFromTarget;
	static double lengthError;
	static double[] centerX;

	
	static{ 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	protected void initDefaultCommand() {
		
	}
	
	// Loads Camera Data and sets variables for the dimensions.
	public void loadCameraData() {
//		opens up the camera stream and tries to load it
		videoCapture = new VideoCapture();
		tracker = new LiftTracker();
//		videoCapture.open("http://roborio-6000-frc.local:1181/?action=stream");
		videoCapture.open(1);
		// change that to your team number boi("http://roborio-XXXX-frc.local:1181/?action=stream");
		if(!videoCapture.isOpened()){
			System.out.println("Didn't open Camera, restart jar");
		}
//		time to actually process the acquired images
		else if(videoCapture.isOpened()){
			processImage();
		}
	}
	public static void processImage(){
		System.out.println("Processing Started");
		 matOriginal = new Mat();
		 
		//System.out.println("Hey I'm Processing Something!");
		videoCapture.read(matOriginal);
		tracker.process(matOriginal);
		returnCenterX();
		System.out.println("Angle: " + getAngle());
		System.out.println("Distance: " + distanceFromTarget());
		System.out.println("LengthBetweenContours: " + returnCenterX());
		table.putNumber("distanceFromTarget", distanceFromTarget());
		table.putNumber("angleFromGoal", getAngle());
		table.putNumberArray("centerX", centerX);
		videoCapture.read(matOriginal);
		
	}
	public static double returnCenterX(){
		double[] defaultValue = new double[0];
			// This is the center value returned by GRIP thank WPI
			if(!tracker.filterContoursOutput.isEmpty() && tracker.filterContoursOutput.size() >= 2){
				Rect r = Imgproc.boundingRect(tracker.filterContoursOutput.get(1));
				Rect r1 = Imgproc.boundingRect(tracker.filterContoursOutput.get(0)); 
				centerX = new double[]{r1.x + (r1.width / 2), r.x + (r.width / 2)};
				Imgcodecs.imwrite("output.png", matOriginal);
				//System.out.println(centerX.length); //testing
				// this again checks for the 2 shapes on the target
				if(centerX.length == 2){
					// subtracts one another to get length in pixels
					lengthBetweenContours = Math.abs(centerX[0] - centerX[1]);
				}
			}
		return lengthBetweenContours;
	}
	
	public static double distanceFromTarget(){
		// distance costant divided by length between centers of contours
		distanceFromTarget = DISTANCE_CONSTANT / lengthBetweenContours;
		return distanceFromTarget - OFFSET_TO_FRONT; 
	}
	
	public static double getAngle(){
		// 9.25in is for the distance from center to center from goal, then divide by lengthBetweenCenters in pixels to get proportion
		double constant = WIDTH_BETWEEN_TARGET / lengthBetweenContours;
		double angleToGoal = 0;
			//Looking for the 2 blocks to actually start trig
		if(!tracker.filterContoursOutput.isEmpty() && tracker.filterContoursOutput.size() >= 2){

			if(centerX.length == 2){
				// this calculates the distance from the center of goal to center of webcam 
				double distanceFromCenterPixels= ((centerX[0] + centerX[1]) / 2) - (CAMERA_WIDTH / 2);
				// Converts pixels to inches using the constant from above.
//				double distanceFromCenterInch = distanceFromCenterPixels * constant;
				
				// math brought to you by Jacob
				angleToGoal = Math.atan((distanceFromCenterPixels * constant)/ (distanceFromTarget() + 13));
				angleToGoal = Math.toDegrees(angleToGoal);
				// prints angle
				//System.out.println("Angle: " + angleToGoal);
				}
			}
			return angleToGoal;
	}
}
