package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor shooterWheel;
	
	public Shooter(){
		shooterWheel = null;
	}
	
	public void spinShooterWheel(){
		shooterWheel.set(.6);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

