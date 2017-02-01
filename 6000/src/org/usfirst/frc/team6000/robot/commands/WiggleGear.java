package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WiggleGear extends Command{
		
	boolean isDone;
	double startTime;
	Timer wiggleTimer = new Timer();
	
	protected void end() {
		
	}

	protected void execute() {
		if (wiggleTimer.get() > 0 && wiggleTimer.get() < 0.25) {
			RobotMap.leftMotor.set(0.4);
			RobotMap.rightMotor.set(0.4);
		} else if (wiggleTimer.get() > 0.25 && wiggleTimer.get() < 0.5) {
			RobotMap.leftMotor.set(-0.4);
			RobotMap.rightMotor.set(-0.4);
		} else if (wiggleTimer.get() > 0.5 && wiggleTimer.get() < 0.75) {
			RobotMap.leftMotor.set(0.4);
			RobotMap.rightMotor.set(0.4);
		} else if (wiggleTimer.get() > 0.75 && wiggleTimer.get() < 1) {
			RobotMap.leftMotor.set(-0.4);
			RobotMap.rightMotor.set(-0.4);
		} else isDone = true;
	}

	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	protected void interrupted() {
		
	}

	protected boolean isFinished() {
		if (isDone) {
			RobotMap.leftMotor.set(0);
			RobotMap.rightMotor.set(0);
			return true;	
		}
		return false;
	}

}
