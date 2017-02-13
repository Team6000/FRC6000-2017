package org.usfirst.frc.team6000.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	
	public static int leftDrivePort = 0;
	public static int rightDrivePort = 1;

	
	public static int leftEncoderPortA = 0;
	public static int leftEncoderPortB = 1;
	public static int rightEncoderPortA = 2;
	public static int rightEncoderPortB = 3;
	
	public static int intakePort = 2;
	public static int shooterPort = 3;
	public static int indexerPort = 4;
	public static int climberPort = 5;
	
	
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
}
