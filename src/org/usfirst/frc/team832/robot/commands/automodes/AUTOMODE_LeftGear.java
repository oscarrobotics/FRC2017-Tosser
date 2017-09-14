package org.usfirst.frc.team832.robot.commands.automodes;

import org.usfirst.frc.team832.robot.commands.auto.AutoDriveDistanceNEW;
import org.usfirst.frc.team832.robot.commands.auto.AutoSpearGear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTOMODE_LeftGear extends CommandGroup {

    public AUTOMODE_LeftGear() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new AutoDriveDistanceNEW(-.5, 0, 860*4, 0));
//    	addSequential(new AutoSpinToPosition(60));
    	addSequential(new AutoDriveDistanceNEW(-.35, 0, 960*4, 60));
    	addSequential(new AutoSpearGear());
    }
}
