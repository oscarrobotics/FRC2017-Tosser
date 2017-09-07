package org.usfirst.frc.team832.robot.subsystems;

import org.usfirst.frc.team832.robot.RobotMap;
import org.usfirst.frc.team832.robot.commands.teleop.*;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class WestCoastDrive extends Subsystem {

    public final RobotDrive westCoast = RobotMap.westCoast;
    public final boolean kadeDriving = false;
    public void initDefaultCommand() {
    	if (kadeDriving) {
    		setDefaultCommand(new KadeDrive());
    		
    	} else {
    		setDefaultCommand(new DriveWithJoysticks());
    	}
    }
    
    public void kadeDriveInputs(double drive, double rotation) {
    	RobotMap.westCoast.arcadeDrive(drive, rotation);
    }
    
    public void takeJoystickInputs(double left, double right) {
//    	double leftCubed = left * left * left;
//    	double rightCubed = right * right * right;
    	double leftCubed = left;
    	double rightCubed = right;
    	RobotMap.westCoast.tankDrive(leftCubed, rightCubed);
    }
    
    public void autoDriveArcade(double pow, double rot) {
    	RobotMap.westCoast.arcadeDrive(pow, rot, false);
    }
    public void changeMode(TalonControlMode controlMode){
      
    	RobotMap.left1.changeControlMode(controlMode);
    	RobotMap.right1.changeControlMode(controlMode);
       
       
    }
    public double getDistance() {
    	double leftD, rightD;
    	leftD = RobotMap.left1.getEncPosition();
    	rightD = RobotMap.right1.getEncPosition();
    	return (leftD + rightD)/2;
    }
    
    public double getMin()
    {
    	return Math.min(RobotMap.left1.getEncPosition(), RobotMap.right1.getEncPosition());
    }
    
    public double getMax()
    {
    	return Math.max(RobotMap.left1.getEncPosition(), RobotMap.right1.getEncPosition());
    	
    }
    
    public void stop() {
		RobotMap.westCoast.tankDrive(0,0);
	}
   
}

