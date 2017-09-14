package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AutoSpearGear extends InstantCommand {

    public AutoSpearGear() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumatics);
    }

    // Called once when the command executes
    protected void initialize() {
    	RobotMap.gearHolderSol.set(Value.kForward);
    }

}
