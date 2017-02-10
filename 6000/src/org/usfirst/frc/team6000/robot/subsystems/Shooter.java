package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.RobotMap;
	

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
	
	Spark shooter = new Spark(RobotMap.shooterPort);
	
	public Shooter(){
		
	}
	
	public void spinShooterWheel(){
		shooter.set(.6);
	}
	
	public void stopShooterWheel() {
		shooter.set(0);
	}

    public void initDefaultCommand() {
    	shooter.set(0);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

