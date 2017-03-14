package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class AutoDriveDistance extends Command {

	double power,delay,distance,leftPower,rightPower;
	final double ticks=512, wheelCirc=Math.PI*4;
    public AutoDriveDistance(double power, double delay,double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.power=power;
    	this.delay=delay;
    	this.distance=distance/wheelCirc*ticks;
    	requires(Robot.westCoastDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//RobotMap.left1
    	Robot.westCoastDrive.changeMode(TalonControlMode.PercentVbus);
 
    	RobotMap.left1.setEncPosition(0);
    	RobotMap.right1.setEncPosition(0);
		leftPower=power;
		rightPower=power+.0225;


    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double leftDiffRight = RobotMap.left1.getEncPosition() - RobotMap.right1.getEncPosition();
//    	double rightDiffLeft = RobotMap.right1.getEncPosition() - RobotMap.left1.getEncPosition();;
    	if(Math.signum(power)==-1){
    		if(RobotMap.left1.getEncPosition()>distance)
    			leftPower=0;
    		else 
    			leftPower=power;

    	
    		
    		if(RobotMap.right1.getEncPosition()<-distance)
    			rightPower=0;
    		else
    			rightPower=rightPower;
        }
    	if(Math.signum(power)==1){
    		if(RobotMap.left1.getEncPosition()<-distance)
    			leftPower=0;
    		else 
    			leftPower=power;

    	
    		
    		if(RobotMap.right1.getEncPosition()>distance)
    			rightPower=0;
    		else
    			rightPower=rightPower;
        }
    	
    	
//    	leftPower-=0.05*(leftDiffRight);
//    	rightPower-=0.05*(RobotMap.right1.getEncPosition()-RobotMap.left1.getEncPosition());
    	Robot.westCoastDrive.takeAutoInput(leftPower, rightPower);
    	
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
