package org.usfirst.frc.team832.robot.commands.automodes;

import org.usfirst.frc.team832.robot.commands.auto.AutoDriveDistanceNEW;
import org.usfirst.frc.team832.robot.commands.auto.AutoSpearGear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTOMODE_RightGear extends CommandGroup {

    public AUTOMODE_RightGear() {

    	addSequential(new AutoDriveDistanceNEW(-.5, .25, 830*4, 0));
//    	addSequential(new AutoSpinToPosition(-60));
    	addSequential(new AutoDriveDistanceNEW(-.35, 0, 930*4, -60));
    	addSequential(new AutoSpearGear());
    }
}
