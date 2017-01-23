package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{

	Spark intakeWheel;
	
	protected void initDefaultCommand() {
		intakeWheel.set(0.75);
	}
	
	public void startIntake() {
		intakeWheel.set(0.75);
	}
	
	public void stopIntake() {
		intakeWheel.set(0);
	}

}
