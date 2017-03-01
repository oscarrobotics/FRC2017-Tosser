package org.usfirst.frc.team832.robot.commands;

import org.usfirst.frc.team832.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RPMTo2000 extends Command {

    public RPMTo2000() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setActualRPM(2000);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
