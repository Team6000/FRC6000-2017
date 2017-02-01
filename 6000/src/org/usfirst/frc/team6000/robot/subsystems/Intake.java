package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Intake extends Subsystem{
	
	protected void initDefaultCommand() {
		RobotMap.intakeWheel.set(0.75);
	}
	
	public void startIntake() {
		RobotMap.intakeWheel.set(0.75);
	}
	
	public void stopIntake() {
		RobotMap.intakeWheel.set(0);
	}

}
