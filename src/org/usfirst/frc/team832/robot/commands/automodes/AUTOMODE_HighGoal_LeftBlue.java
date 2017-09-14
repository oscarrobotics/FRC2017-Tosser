package org.usfirst.frc.team832.robot.commands.automodes;

import java.util.Timer;

import org.usfirst.frc.team832.robot.commands.auto.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import sun.security.timestamp.Timestamper;

/**
 *
 */
public class AUTOMODE_HighGoal_LeftBlue extends CommandGroup {

    public AUTOMODE_HighGoal_LeftBlue() {
    	addSequential(new AutoSpinUpShooter(2000));
    	addSequential(new AutoTurntableCW());
    	//addSequential
    }
}
