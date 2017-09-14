package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpinUpShooter extends Command {

	double rpm;
	
    public AutoSpinUpShooter(double rpm) {
        // Use requires() here to declare subsystem dependencies
    	this.rpm = rpm;
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.runShooter(rpm);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooter.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
