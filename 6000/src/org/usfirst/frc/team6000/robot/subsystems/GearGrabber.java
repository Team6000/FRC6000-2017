package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearGrabber extends Subsystem {
	// Possibly sense distance from target to more accurately place gear

	DoubleSolenoid piston = new DoubleSolenoid(1, 1);
	
	protected void initDefaultCommand() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void openGrabber() {
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closeGrabber() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
}
