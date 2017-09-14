package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSpinToPosition extends Command {
	
	double target;
	private boolean finished = false, hasStarted = false;
	private int executeCount = 0;

    public AutoSpinToPosition(double targetInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.westCoastDrive);
//    	requires(Robot.gyroPid);
    	target = targetInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//RobotMap.navx.reset();
    	//RobotMap.navx.zeroYaw();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    
    protected void execute() {
    	Robot.gyroPid.setSetpoint(target);
    	Robot.westCoastDrive.autoDriveArcade(0, Robot.gyroPid.pidOut);
    	
    	finished = Robot.gyroPid.onTarget();
    	
    	executeCount++;
    	
    	SmartDashboard.putString("Command", "RotateToPosition");
    	SmartDashboard.putNumber("Executions", executeCount);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gyroPid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.gyroPid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}
