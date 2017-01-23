package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

/* Shoot Instructions:
 * 
 * [ ] Set shooter elevation
 * [x] Set wheel speed (to 0.6)
 * [ ] Feed balls into shooter
 * 
 */

public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark shooterWheel;
	
	public Shooter(){
		shooterWheel = null;
	}
	
	public void spinShooterWheel(){
		shooterWheel.set(.6);
	}
	
	public void stopShooterWheel() {
		shooterWheel.set(0);
	}

    public void initDefaultCommand() {
    	shooterWheel.set(0);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

