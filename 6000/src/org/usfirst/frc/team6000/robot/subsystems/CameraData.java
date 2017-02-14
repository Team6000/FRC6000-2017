package org.usfirst.frc.team6000.robot.subsystems;
import java.awt.Point;
import org.usfirst.frc.team6000.robot.subsystems.Line;

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
	/*
	 * Test for finding P1-P8
	 * Put the lines into a line object that has these settings:
	 *   x1,y1
	 *   x2,y2
	 *   vertical or horizontal
	 *   which line is it apart of
	 * To test if it is vertical or horizontal, find the slope between the lines.
	 *   If it is less than 45 degrees, its horizontal.  More, then it is vertical.
	 * If vertical, to test which line it is apart of (which means also which tape it is apart of)
	 *   Test to see if there are other vertical lines to the right of it
	 *   Give it like a 15 pixel or so threshold to not check so that you do not check vertical lines within your own line
	 *   If there are three vertical lines to the right of it, then the ceurrent line is in the first line.  If its two...
	 * Test within each big line which line is the highest and which is the lowest and use those to find P1-P8
	 */
	
	Line l1 = new Line();
	Line l2 = new Line();
	Line l3 = new Line();
	Line l4 = new Line();
	Line l5 = new Line();
	Line l6 = new Line();
	Line l7 = new Line();
	Line l8 = new Line();
	
	double[] defaultValue = new double[0];
	
	public void popLines(){
		while(true){
			Line l = new Line();
			double[] point1 = lines.getNumberArray("p1", defaultValue);
			double[] point2 = lines.getNumberArray("p2", defaultValue);
			l.x1 = point1[0];
			l.y1 = point1[1];
			l.x2 = point2[0];
			l.y2 = point2[1];
			
			// Calculate slope
			if(l.x1 != l.x2){
				double slope = (l.y2-l.y1)/(l.x2-l.x1);
				if(Math.abs(slope)>1){
					l.alignment = 'v';
				}
				else if(Math.abs(slope)<1){
					l.alignment = 'h';
				}
			}
			else{
				l.alignment = 'v';
			}
			// If the line is horizontal, throw it away and start again with another line
			if(l.alignment == 'h'){
				break;
			}
			else if (l.alignment == 'v'){
				
			}
			else{
				System.out.println("The line is not set up correctly");
				break;
			}
		}
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
	 *       1			3			5			7
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       |			|			|			|
	 *       2			4			6			8
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
