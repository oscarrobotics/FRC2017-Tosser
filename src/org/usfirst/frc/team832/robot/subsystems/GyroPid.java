package org.usfirst.frc.team832.robot.subsystems;

import org.usfirst.frc.team832.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroPid extends PIDSubsystem {

	final static double gyrokP = 0.02;
	final static double gyrokI = 0;
	final static double gyrokD = 0.069;
	final static double gyrokF = 0.49;
	public double pidOut;
	
    // Initialize your subsystem here
    public GyroPid() {
        super("GyroPid", gyrokP, gyrokI, gyrokD, gyrokF, 0.015);
        getPIDController().setContinuous(true);
        setAbsoluteTolerance(2.5);
        if (RobotMap.navx.isConnected() && !RobotMap.navx.isCalibrating()) {
        	enable();
        }
        
        setOutputRange(-.7, .7);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        return RobotMap.navx.getAngle();
    }

    protected void usePIDOutput(double output) {
    	double actualOut;
    	
    	SmartDashboard.putBoolean("onTarget", onTarget());
    	
    	if (onTarget()) {
    		actualOut = 0;
    	} else actualOut = -output;
    	pidOut = actualOut;
    	SmartDashboard.putNumber("PidOut", actualOut);        
    }
}
