package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Climber extends Subsystem{
	
	private Spark climberWheel = new Spark(RobotMap.climberPort);
	
	protected void initDefaultCommand() {
		
	}
	
	public void startClimb() {
		climberWheel.set(0.75);
	}
	
	public void stopClimb() {
		climberWheel.set(0);
	}

}
