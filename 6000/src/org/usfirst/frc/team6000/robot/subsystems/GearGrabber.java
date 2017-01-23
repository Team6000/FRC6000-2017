package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearGrabber extends Subsystem {
	// Possibly sense distance from target to more accurately place gear

	DoubleSolenoid piston = new DoubleSolenoid(0, 0);
	
		protected void initDefaultCommand() {
			
		}
}
