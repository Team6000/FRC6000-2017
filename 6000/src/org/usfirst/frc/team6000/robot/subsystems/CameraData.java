package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.subsystems.CPoint;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.subsystems.Line;
import java.util.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CameraData {
	NetworkTable countours,lines;
	
	//initialize
	//This connects us to the NetworkTables
	//
	public CameraData(){
//		lines = NetworkTable.getTable("GRIP/myCountoursReport");
//		countours = NetworkTable.getTable("GRIP/myLinesReport");
	}
	
	//find distance between two Points
	public double findDistance(CPoint pt1, CPoint pt2){
		double x1,x2,y1,y2;
		x1 = pt1.x;
		y1 = pt1.y;
		x2 = pt2.x;
		y2 = pt2.y;
		
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
	
	List<Line> checkLines;
	
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
	
	public void doProcess(){
		checkLines = new ArrayList<Line>();
		popLines();
		segregateLines();
		cutMedians();
		System.out.println("Max1: " + max1);
		System.out.println("Max2: " + max2);
		System.out.println("Max3: " + max3);
		System.out.println("Max4: " + max4);
		System.out.println("Min1: " + min1);
		System.out.println("Min2: " + min2);
		System.out.println("Min3: " + min3);
		System.out.println("Min4: " + min4);
	}
	
	public void popLines(){
		for(int i=0;i<Robot.pipeline.findLinesOutput().size();i++){
			Line l = new Line();
			
			l.x1 = Robot.pipeline.findLinesOutput().get(i).x1;
			l.y1 = Robot.pipeline.findLinesOutput().get(i).y1;
			l.x2 = Robot.pipeline.findLinesOutput().get(i).x2;
			l.y2 = Robot.pipeline.findLinesOutput().get(i).y2;
			
			// Calculate slope
			if(l.x1 != l.x2){
				double slope = (l.y2-l.y1)/(l.x2-l.x1);
				if(Math.abs(slope)>1){
					l.alignment = 'v';
//					System.out.println(i + ":" + l.x1);
//					System.out.println(i + ":" + l.y1);
//					System.out.println(i + ":" + l.x2);
//					System.out.println(i + ":" + l.y2);
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
			}
			else if (l.alignment == 'v'){
				checkLines.add(l);
				
//				System.out.println("Vertical: " + (int) l.x1 + "," + (int) l.y1);
			}
			else{
				System.out.println("The line is not set up correctly");
			}
		}
	}
	
	public void segregateLines(){
		double cursor = 0;
		double lineNumber = 4;
		double leeWay = 20;
		System.out.println(checkLines.size());
		for(int i = 0; i<checkLines.size(); i++){
			lineNumber = 4;
			cursor = (int) checkLines.get(i).x1;
			// Go through every single vertical line to see if it has three ahead of it
			cursor += leeWay;
			for(int j = 0; j<ImageRecognition.SCREEN_WIDTH - checkLines.get(i).x1; j++){
				cursor ++;
				for (int k = 0; k<checkLines.size(); k++){
					if(cursor == (int) checkLines.get(k).x1){
						lineNumber --;
						cursor += leeWay;
						break;
					}
					else{
						
					}
				}
			}
			checkLines.get(i).bigLine = lineNumber;
//			System.out.println(lineNumber);
		}
	}
	
	public void cutMedians(){
		double max = 0;
		double min = 720;
		double lineX = 0;
		for (int i = 1; i<5; i++){
			max = 480;
			min = 0;
			
			for (int j = 0; j<checkLines.size(); j++){
				// if the lines being checked are in the same big line
				if(checkLines.get(j).bigLine == i){
					// set the max number to be the highest lines top point y value
					if(checkLines.get(j).y1 < max || checkLines.get(j).y2 < max)
					{
						if(checkLines.get(j).y1 < checkLines.get(j).y2){
							lineX = checkLines.get(j).x1;
							max = checkLines.get(j).y1;
						}
						else{
							lineX = checkLines.get(j).x2;
							max = checkLines.get(j).y2;
						}
					}
					
					if(checkLines.get(j).y1 > min || checkLines.get(j).y2 > min){
						if(checkLines.get(j).y1 > checkLines.get(j).y2){
							lineX = checkLines.get(j).x1;
							min = checkLines.get(j).y1;
						}
						else{
							lineX = checkLines.get(j).x2;
							min = checkLines.get(j).y2;
						}
					}
				}
				else{
					
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
	
	public CPoint getP1(){
		CPoint P1 = new CPoint();
		P1.x = (int) lineX1;
		P1.y = (int) max1;
		return P1;
	}
	public CPoint getP2(){
		CPoint P2 = new CPoint();
		P2.x = (int) lineX2;
		P2.y = (int) max2;
		return P2;
	}
	public CPoint getP3(){
		CPoint P3 = new CPoint();
		P3.x = (int) lineX1;
		P3.y = (int) min1;
		return P3;
	}
	public CPoint getP4(){
		CPoint P4 = new CPoint();
		P4.x = (int) lineX2;
		P4.y = (int) min2;
		return P4;
	}
	public CPoint getP5(){
		CPoint P5 = new CPoint();
		P5.x = (int) lineX3;
		P5.y = (int) max3;
		return P5;
	}
	public CPoint getP6(){
		CPoint P6 = new CPoint();
		P6.x = (int) lineX4;
		P6.y = (int) max4;
		return P6;
	}
	public CPoint getP7(){
		CPoint P7 = new CPoint();
		P7.x = (int) lineX3;
		P7.y = (int) min3;
		return P7;
		
	}
	public CPoint getP8(){
		CPoint P8 = new CPoint();
		P8.x = (int) lineX4;
		P8.y = (int) min4;
		return P8;
		
	}

	public int CAM_WIDTH(){
		return 1280;
	}
}
