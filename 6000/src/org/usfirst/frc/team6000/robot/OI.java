package org.usfirst.frc.team6000.robot;

//import org.usfirst.frc.team6000.robot.commands.IntakeBalls;
//import org.usfirst.frc.team6000.robot.commands.PathfinderTest;
import org.usfirst.frc.team6000.robot.commands.PlaceGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

//	public static Joystick secondaryStick = new Joystick(2);

	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);

//	public static Button shootButton = new JoystickButton(rightStick, 2);
//
	public static Button runRotateButton = new JoystickButton(rightStick, 3);
//
//	public static Button shootButton = new JoystickButton(secondaryStick, 2);
//	public static Button intakeButton = new JoystickButton(secondaryStick, 1);
//	public static Button placeGearBtn = new JoystickButton(secondaryStick, 3);
//	public static Button stopPlaceGearBtn = new JoystickButton(secondaryStick, 4);

//github.com/Team6000/FRC6000-2017.git
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public OI(){
//
//		shootButton.whileHeld(new Shoot());
//		intakeButton.whileHeld(new IntakeBalls());
//		shootButton.whenReleased(new StopShoot());

//		intakeButton.whenReleased(new StopIntake());
		
//		placeGearBtn.whenPressed(new PlaceGear());
		
		SmartDashboard.putData("PlaceGear", new PlaceGear());

		runRotateButton.whenPressed(new PlaceGear());

	}
}

