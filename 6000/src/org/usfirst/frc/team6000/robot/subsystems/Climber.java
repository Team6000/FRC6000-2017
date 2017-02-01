package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;

public class Climber extends Subsystem{
	
	protected void initDefaultCommand() {
		RobotMap.climberWheel.set(0);
	}
	
	public void startClimb() {
		RobotMap.climberWheel.set(0.75);
	}
	
	public void stopClimb() {
		RobotMap.climberWheel.set(0);
	}

}
