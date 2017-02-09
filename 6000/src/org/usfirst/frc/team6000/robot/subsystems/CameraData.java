package org.usfirst.frc.team6000.robot.subsystems;
import java.awt.Point;


import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CameraData {
	NetworkTable countours,lines;
	
	//initialize
	//This connects us to the NetworkTables
	//
	public CameraData(){
		lines = NetworkTable.getTable("GRIP/myCountoursReport");
		countours = NetworkTable.getTable("GRIP/myLinesReport");
	}
	
	//find distance between two Points
	private double findDistance(Point pt1, Point pt2){
		double x1,x2,y1,y2;
		x1 = pt1.getX();
		y1 = pt1.getY();
		x2 = pt2.getX();
		y2 = pt2.getY();
		
		return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
	}
				
	
	//
	//GETTERS
	//
	public boolean pointedAtGrip(){
		return true;
	}
	
	/*
	 *       p1---------p2          p5---------p6
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       p3---------p4          p7---------p8
	 * 
	 */
	public Point getP1(){
		return null;
	}
	public Point getP2(){
		return null;
	}
	public Point getP3(){
		return null;
	}
	public Point getP4(){
		return null;
	}
	public Point getP5(){
		return null;
		
	}
	public Point getP6(){
		return null;
		
	}
	public Point getP7(){
		return null;
		
	}
	public Point getP8(){
		return null;
		
	}
	public int camWidth(){
		return 0;
	}

	public int CAM_WIDTH(){
		return 0;
	}
}
