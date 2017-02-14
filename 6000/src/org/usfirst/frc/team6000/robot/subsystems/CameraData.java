package org.usfirst.frc.team6000.robot.subsystems;
import java.awt.Point;
import org.usfirst.frc.team6000.robot.subsystems.Line;
import java.util.*;

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
	
	List<Line> checkLines = new ArrayList<>();
	
	double max1 = 0;
	double max2 = 0;
	double max3 = 0;
	double max4 = 0;
	double min1 = 0;
	double min2 = 0;
	double min3 = 0;
	double min4 = 0;
	
	double lineX1 = 0;
	double lineX2 = 0;
	double lineX3 = 0;
	double lineX4 = 0;
	
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
				checkLines.add(l);
			}
			else{
				System.out.println("The line is not set up correctly");
				break;
			}
		}
	}
	
	public void segregateLines(){
		double cursor = 0;
		double lineNumber = 0;
		double leeWay = 15;
		for(int i = 0; i<checkLines.size(); i++){
			cursor = checkLines.get(i).x1;
			// Go through every single vertical line to see if it has three ahead of it
			cursor += leeWay;
			for(int j = 0; j<1280-checkLines.get(i).x1; j++){
				cursor ++;
				for (int k = 0; k<checkLines.size(); k++){
					if(cursor == checkLines.get(k).x1){
						lineNumber ++;
						cursor += leeWay;
						break;
					}
				}
			}
			checkLines.get(i).bigLine = lineNumber;
		}
	}
	
	public void cutMedians(){
		double max = 0;
		double min = 720;
		double lineX = 0;
		for (int i = 1; i<5; i++){
			
			for (int j = 0; j<checkLines.size(); j++){
				// if the lines being checked are in the same big line
				if(checkLines.get(j).bigLine == i){
					// set the max number to be the highest lines top point y value
					if(checkLines.get(j).y1 > max || checkLines.get(j).y2 > max)
					{
						if(checkLines.get(j).y1 > checkLines.get(j).y2){
							lineX = checkLines.get(j).x1;
							max = checkLines.get(j).y1;
						}
						else{
							lineX = checkLines.get(j).x2;
							max = checkLines.get(j).y2;
						}
					}
					
					if(checkLines.get(j).y1 < min || checkLines.get(j).y2 < min){
						if(checkLines.get(j).y1 < checkLines.get(j).y2){
							lineX = checkLines.get(j).x1;
							min = checkLines.get(j).y1;
						}
						else{
							lineX = checkLines.get(j).x2;
							min = checkLines.get(j).y2;
						}
					}
				}
			}
			// assign the values found to the global variables for P1-P8;
			switch(i){
				case 1:
					lineX1 = lineX;
					max1=max;
					min1=min;
					break;
				case 2:
					lineX2 = lineX;
					max2=max;
					min2=min;
					break;
				case 3:
					lineX3 = lineX;
					max3=max;
					min3=min;
					break;
				case 4:
					lineX4 = lineX;
					max4=max;
					min4=min;
					break;
				default:
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
		Point P1 = new Point();
		P1.x = (int) lineX1;
		P1.y = (int) max1;
		return P1;
	}
	public Point getP2(){
		Point P2 = new Point();
		P2.x = (int) lineX2;
		P2.y = (int) max2;
		return P2;
	}
	public Point getP3(){
		Point P3 = new Point();
		P3.x = (int) lineX1;
		P3.y = (int) min1;
		return P3;
	}
	public Point getP4(){
		Point P4 = new Point();
		P4.x = (int) lineX2;
		P4.y = (int) min2;
		return P4;
	}
	public Point getP5(){
		Point P5 = new Point();
		P5.x = (int) lineX3;
		P5.y = (int) max3;
		return P5;
	}
	public Point getP6(){
		Point P6 = new Point();
		P6.x = (int) lineX4;
		P6.y = (int) max4;
		return P6;
	}
	public Point getP7(){
		Point P7 = new Point();
		P7.x = (int) lineX3;
		P7.y = (int) min3;
		return P7;
		
	}
	public Point getP8(){
		Point P8 = new Point();
		P8.x = (int) lineX4;
		P8.y = (int) min4;
		return P8;
		
	}

	public int CAM_WIDTH(){
		return 1280;
	}
}
