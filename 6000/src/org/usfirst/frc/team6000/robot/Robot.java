
package org.usfirst.frc.team6000.robot;

import org.usfirst.frc.team6000.robot.subsystems.DriveTrain;

import org.usfirst.frc.team6000.robot.subsystems.Shooter;
import org.usfirst.frc.team6000.robot.subsystems.ImageRecognition;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team6000.robot.subsystems.Indexer;
import org.usfirst.frc.team6000.robot.commands.PathfinderTest;
//import org.usfirst.frc.team6000.robot.subsystems.CameraData;

//import org.usfirst.frc.team6000.robot.subsystems.Climber;
//import org.usfirst.frc.team6000.robot.subsystems.GearGrabber;
import org.usfirst.frc.team6000.robot.subsystems.Intake;
//import org.usfirst.frc.team6000.robot.subsystems.PiplelieOne;

import org.usfirst.frc.team6000.robot.subsystems.Climber;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final Indexer indexer = new Indexer();
	public static OI oi;
	public static final ImageRecognition imgRec = new ImageRecognition();
//	public static final PiplelieOne pipeline = new PiplelieOne();
//	public static final CameraData cmData = new CameraData();

    Command autonomousCommand;
    SendableChooser chooser;
    public static AHRS ahrs;
    
    public static UsbCamera camera; 
//	public static CvSink cvSink;
//	public static CvSource imgOutput;
//	public static Mat source = new Mat();
    
    VisionThread visionThread;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    @Override
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto Selector", chooser);
        
        try {
			ahrs =  new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			 DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
//    	System.out.println("HERE");
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	camera.setResolution(640, 360);
    	
//    	imgOutput = CameraServer.getInstance().putVideo("testOutput", 640, 480);
    	
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
//        autonomousCommand = new PathfinderTest();
        
		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		
		switch(autoSelected) {
		case "1,1":
			System.out.println("pos1");
			autonomousCommand = new PathfinderTest(1,1);
			break;
		case "1,2":
			System.out.println("pos1");
			autonomousCommand = new PathfinderTest(1,2);
			break;
		case "1,3":
			System.out.println("pos1");
			autonomousCommand = new PathfinderTest(1,3);
			break;
		case "2,1":
			System.out.println("pos2");
			autonomousCommand = new PathfinderTest(2,1);
			break;
		case "2,2":
			System.out.println("pos2");
			autonomousCommand = new PathfinderTest(2,2);
			break;
		case "2,3":
			System.out.println("pos2");
			autonomousCommand = new PathfinderTest(2,3);
			break;
		case "3,1":
			System.out.println("pos3");
			autonomousCommand = new PathfinderTest(3,1);
			break;
		case "3,2":
			System.out.println("pos3");
			autonomousCommand = new PathfinderTest(3,2);
			break;
		case "3,3":
			System.out.println("pos3");
			autonomousCommand = new PathfinderTest(3,3);
			break;
		default:
			System.out.println("default");
//			autonomousCommand = new PathfinderTest(1,3);
			break;
		}
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) autonomousCommand.cancel();
//        System.out.println("TELEOP Started");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        System.out.println("TELEOP Started");
    }
    
    public void runPipeline() {
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        System.out.println("WHAT THE HECK");
    }
}
