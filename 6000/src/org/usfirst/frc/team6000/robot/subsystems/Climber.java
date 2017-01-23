package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{

	Spark climberWheel;
	
	protected void initDefaultCommand() {
		climberWheel.set(0);
	}
	
	public void startClimb() {
		climberWheel.set(0.75);
	}
	
	public void stopClimb() {
		climberWheel.set(0);
	}

}
