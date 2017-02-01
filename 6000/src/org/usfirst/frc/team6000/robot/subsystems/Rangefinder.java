package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Rangefinder extends Subsystem{
		
	public Rangefinder(int output, int input) { // (ECHO PULSE OUTPUT, TRIGGER PULSE INPUT)
		RobotMap.ultra = new Ultrasonic(output, input);
	}

	protected void initDefaultCommand() {
		RobotMap.ultra.setAutomaticMode(true);
		sendRange();
	}
	
	public void sendRange() {
		SmartDashboard.putNumber("Frontal Range (inches)", RobotMap.ultra.getRangeInches());
	}
	
}
