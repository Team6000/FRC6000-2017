package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor leftMotor;
	Victor rightMotor;
	RobotDrive robotDrive = new RobotDrive(leftMotor, rightMotor);
	
	public void setLeft(double speed){
		leftMotor.set(speed);
	}
	
	public void setRight(double speed){
		rightMotor.set(speed);
	}
	
	public void rawDrive(double left, double right){
		rightMotor.set(right);
		leftMotor.set(left);
	}
	
	public void tankDrive(Joystick leftStick, Joystick rightStick){
		robotDrive.tankDrive(leftStick, rightStick);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

