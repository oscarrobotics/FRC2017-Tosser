package org.usfirst.frc.team832.robot.commands;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;
import org.usfirst.frc.team832.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchPull extends Command {

    public WinchPull() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.bigWinch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.bigWinch.set(.10);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.bigWinch.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
