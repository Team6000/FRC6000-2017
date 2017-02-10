package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Intake extends Subsystem{
	
	Spark intake = new Spark(RobotMap.intakePort);
	
	protected void initDefaultCommand() {
		intake.set(0.75);
	}
	
	public void startIntake() {
		intake.set(0.75);
	}
	
	public void stopIntake() {
		intake.set(0);
	}

}
