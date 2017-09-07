package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;
import org.usfirst.frc.team832.robot.util.Conversions;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class AutoDriveDistanceNEW extends Command {

	protected final double power, delay, distance, angle;
	protected double leftD, rightD;

	public AutoDriveDistanceNEW(double power, double delay, double distance, double angleIn) {
		this.power = power;
		this.delay = delay;
		this.distance = (distance);
		this.angle = angleIn;
		requires(Robot.westCoastDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		RobotMap.left1.setEncPosition(0);
		RobotMap.right1.setEncPosition(0);		
		//Robot.westCoastDrivePID.getPIDController().reset();
		Timer.delay(delay);
		Robot.gyroPid.setSetpoint(angle);
		Robot.gyroPid.enable();
	
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		// Update motors
		Robot.westCoastDrive.autoDriveArcade(power, Robot.gyroPid.pidOut);
		
//		if (distance <= Math.abs(Robot.westCoastDrive.getDistance())) {
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//double absDist = Math.abs(Robot.westCoastDrive.getMin());
		return (distance <= Math.abs(Robot.westCoastDrive.getMin()));
		//return false; // for testing purposes only
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.westCoastDrive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
