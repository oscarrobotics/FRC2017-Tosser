package org.usfirst.frc.team832.robot.subsystems;

import org.usfirst.frc.team832.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	public static CANTalon shooterMotor = RobotMap.shooterMotor1;
	public int shooterRPM = 1815;
	
	public void stopShooter() {
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterMotor.set(0);
	}
	
	public void setActualRPM(int rpm) {
		shooterRPM = rpm;
	}
	
	public int getActualRPM() {
		return shooterRPM;
	}
	
	public void runShooter() {
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotor.set(shooterRPM);
	}
	
	public void runShooter(double rpmBoost) {
		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotor.set(shooterRPM + rpmBoost);
	}

	public boolean onTarget() {
		return (shooterMotor.getSetpoint() - 200 <= shooterMotor.getSpeed());
	}
	
	public void initDefaultCommand() {
	}
}
