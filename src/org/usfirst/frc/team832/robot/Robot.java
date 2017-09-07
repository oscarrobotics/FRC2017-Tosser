
package org.usfirst.frc.team832.robot;

import com.ctre.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team832.robot.commands.teleop.*;
import org.usfirst.frc.team832.robot.commands.auto.*;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_CenterGear;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_DoNothing;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_DriveForward;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_HighGoal_LeftBlue;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_LeftGear;
import org.usfirst.frc.team832.robot.commands.automodes.AUTOMODE_RightGear;
import org.usfirst.frc.team832.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Shooter shooter;
	public static WestCoastDrive westCoastDrive;
	public static GyroPid gyroPid;
	public static Pneumatics pneumatics;
	public static Collector collector;
	public static Winch bigWinch;
	public static Turntable turnTable;
	public static OI oi;

	// public static double shooterSetRPM =
	// RobotMap.shooterMotor1.getSetpoint(); // what the shooter should be set
	// at
	// public static double shooterActualRPM =
	// RobotMap.shooterMotor1.getSpeed(); // what it is at
	// public static double shooterCurrentDraw =
	// RobotMap.shooterMotor1.getOutputCurrent() +
	// RobotMap.shooterMotor2.getOutputCurrent();

	// public static AHRS navx = RobotMap.navx;
	
	static RobotMode currentRobotMode = RobotMode.INIT, previousRobotMode;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		CameraServer.getInstance().startAutomaticCapture(0).setVideoMode(PixelFormat.kMJPEG, 320, 240, 15);

		RobotMap.init();

		shooter = new Shooter();
		turnTable = new Turntable();
		westCoastDrive = new WestCoastDrive();
		gyroPid = new GyroPid();
		pneumatics = new Pneumatics();
		collector = new Collector();
		bigWinch = new Winch();
		oi = new OI();
		autonomousCommand = new AUTOMODE_DriveForward();
		Robot.pneumatics.shiftToLow();
		chooser.addDefault("Drive Forward", new AUTOMODE_DriveForward());
		// chooser.addObject("Center Gear", new AUTOMODE_CenterPeg());
		chooser.addObject("Center Gear", new AUTOMODE_CenterGear());
		chooser.addObject("Left Gear", new AUTOMODE_LeftGear());
		chooser.addObject("LB-HighShooter", new AUTOMODE_HighGoal_LeftBlue());
		chooser.addObject("Right Gear", new AUTOMODE_RightGear());
		chooser.addObject("Do Nothing", new AUTOMODE_DoNothing());
		SmartDashboard.putData("Auto mode", chooser);
	}

	public void sendData() {
		double leftD = RobotMap.left1.getEncPosition();
		double rightD = RobotMap.right1.getEncPosition();
		SmartDashboard.putBoolean("NavX IsConnected", RobotMap.navx.isConnected());
		SmartDashboard.putNumber("NavX Yaw", RobotMap.navx.getYaw());
		SmartDashboard.putNumber("NavX Angle", RobotMap.navx.getAngle());
		SmartDashboard.putNumber("Left Encoder", leftD);
		SmartDashboard.putNumber("Right Encoder", rightD);
		SmartDashboard.putData("GyroPID", Robot.gyroPid.getPIDController());

		// shooter stuff
		SmartDashboard.putNumber("Shooter Rippems", RobotMap.shooterMotor1.getSpeed());
		SmartDashboard.putNumber("Shooter Setpoint", RobotMap.shooterMotor1.getSetpoint());
		SmartDashboard.putNumber("Shooter Error", RobotMap.shooterMotor1.getClosedLoopError());

		// winch
		SmartDashboard.putNumber("Winch Current", RobotMap.winch.getOutputCurrent());
		
		// drivetrain
		SmartDashboard.putNumber("Left1 Current", RobotMap.left1.getOutputCurrent());
		SmartDashboard.putNumber("Left2 Current", RobotMap.left2.getOutputCurrent());
		SmartDashboard.putNumber("Right1 Current", RobotMap.right1.getOutputCurrent());
		SmartDashboard.putNumber("Right2 Current", RobotMap.right2.getOutputCurrent());

		// SmartDashboard.putNumber("CLError",
		// RobotMap.shooterMotor1.getClosedLoopError());
		// SmartDashboard.putNumber("RPM Actual",
		// RobotMap.shooterMotor1.getSpeed());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		 Robot.shooter.stopShooter();
		 RobotMap.ballDoorSol.set(true);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendData();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		RobotMap.gearHolderSol.set(Value.kReverse);
		RobotMap.ballDoorSol.set(true);
		Robot.pneumatics.shiftToLow();
		sendData();
		// autonomousCommand = chooser.getSelected();

		//
		// String autoSelected = SmartDashboard.getString("Auto Selector",
		// "Default");
		// switch(autoSelected) {
		// case "My Auto":
		// autonomousCommand = new();
		// break;
		// case "Default Auto": default:
		// autonomousCommand = new ExampleCommand(); break; }
		//

		// schedule the autonomous command (example)
		RobotMap.left1.setEncPosition(0);
		RobotMap.right1.setEncPosition(0);
		RobotMap.navx.zeroYaw();
		if (autonomousCommand != null)
			autonomousCommand = chooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		sendData();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		RobotMap.left1.setEncPosition(0);
		RobotMap.right1.setEncPosition(0);
		RobotMap.navx.zeroYaw();
		RobotMap.gearHolderSol.set(Value.kReverse);
		RobotMap.ballDoorSol.set(true);
		Robot.pneumatics.shiftToLow();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (Robot.shooter.shooterMotor.getSpeed() > 1200) {
			RobotMap.ballDoorSol.set(false);
		} else {
			RobotMap.ballDoorSol.set(true);
		}
		sendData();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public static RobotMode getCurrentRobotMode() {
        return currentRobotMode;
    }

    public static RobotMode getPreviousRobotMode() {
        return previousRobotMode;
    }
	
	// This is the end of all methods

}
