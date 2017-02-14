package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Line extends Subsystem {

	public double x1,y1,x2,y2;
	public char alignment;
	public double bigLine;
	
	public Line(){
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		alignment = ' ';
		bigLine = 0;
	}

    public void initDefaultCommand() {

    }
    
}

