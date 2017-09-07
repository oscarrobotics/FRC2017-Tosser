package org.usfirst.frc.team832.robot.commands.teleop;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftHigh extends Command {

	int loops;
	
    public ShiftHigh() {
       requires(Robot.pneumatics);
    }
    protected void execute() {
    	if (RobotMap.winch.getOutputCurrent() > 60) {
    		loops++;
    	}
    	Robot.pneumatics.shiftToHigh();	
    }
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	//Robot.pneumatics.shiftToLow();
    }

    protected void interrupted() {
    	end();
    }
}
