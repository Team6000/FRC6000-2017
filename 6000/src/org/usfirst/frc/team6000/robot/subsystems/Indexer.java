package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Indexer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Spark indexMotor = new Spark(RobotMap.indexerPort);
	
	public void stopBalls(){
		indexMotor.set(-0.6);
	}
	
	public void stopMotor(){
		indexMotor.set(0);
	}
	
	public void indexBalls(){
		indexMotor.set(0.6);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

