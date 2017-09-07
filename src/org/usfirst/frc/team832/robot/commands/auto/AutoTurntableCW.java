package org.usfirst.frc.team832.robot.commands.auto;

import org.usfirst.frc.team832.robot.Robot;
import org.usfirst.frc.team832.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AutoTurntableCW extends InstantCommand {

    public AutoTurntableCW() {
        super();
        requires(Robot.turnTable);
    }

    // Called once when the command executes
    protected void initialize() {
    	RobotMap.turnTable.set(.35);
    }

}
