package org.usfirst.frc.team832.robot.commands.automodes;

import org.usfirst.frc.team832.robot.commands.auto.AutoDriveDistanceNEW;
import org.usfirst.frc.team832.robot.commands.auto.AutoSpearGear;
import org.usfirst.frc.team832.robot.commands.teleop.getGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTOMODE_CenterGear extends CommandGroup {

	public AUTOMODE_CenterGear() {

		// addSequential(new AutoDriveDistance(-0.5, 0.0, -2000, 0));
		addSequential(new AutoDriveDistanceNEW(-0.45, 0.25, 800 * 4, 0));
		addSequential(new AutoSpearGear());
	}
}
