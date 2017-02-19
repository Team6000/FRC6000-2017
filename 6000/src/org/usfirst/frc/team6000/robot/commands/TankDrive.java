package org.usfirst.frc.team6000.robot.commands;

import org.omg.PortableInterceptor.ObjectIdHelper;
import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {
	
//	Joystick leftStick = OI.leftStick;
//    Joystick rightStick = OI.rightStick;
	

    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    Robot.driveTrain.tankDrive(leftStick, rightStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
