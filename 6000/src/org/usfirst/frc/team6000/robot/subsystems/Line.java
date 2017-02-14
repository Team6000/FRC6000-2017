package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Line extends Subsystem {

	public int x1,y1,x2,y2;
	public char alignment;
	public int bigLine;
	
	public Line(){
		
	}

    public void initDefaultCommand() {

    }
    
}

