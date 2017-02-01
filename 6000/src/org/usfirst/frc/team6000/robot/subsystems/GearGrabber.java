package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;

public class GearGrabber extends Subsystem {
	
	protected void initDefaultCommand() {
		RobotMap.piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void openGrabber() {
		RobotMap.piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closeGrabber() {
		RobotMap.piston.set(DoubleSolenoid.Value.kReverse);
	}
}
