package org.usfirst.frc.team832.robot;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.usfirst.frc.team832.robot.subsystems.WestCoastDrive;
import org.usfirst.frc.team832.robot.util.logger.EventLogging;
import org.usfirst.frc.team832.robot.util.logger.EventLogging.Level;
import org.usfirst.frc.team832.robot.util.logger.IDataLogger;
import org.usfirst.frc.team832.robot.util.logger.IDataLoggerDataProvider;

import com.ctre.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Spark;

public class RobotDataLoggerDataProvider implements IDataLoggerDataProvider {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);
	boolean pdpIsPresent = false;
	boolean armTalonIsPresent = false;
	boolean shooterTalonsArePresent = false;

	WestCoastDrive westCoastDrive = Robot.westCoastDrive;

	PowerDistributionPanel powerDistributionPanel = RobotMap.powerDP;
	CANTalon left1 = RobotMap.left1;
	CANTalon left2 = RobotMap.left2;
	CANTalon right1 = RobotMap.right1;
	CANTalon right2 = RobotMap.right2;
	CANTalon shooterMotor1 = RobotMap.shooterMotor1;
	CANTalon shooterMotor2 = RobotMap.shooterMotor2;
	CANTalon winch = RobotMap.winch;
	CANTalon turnTable = RobotMap.turnTable;
	Spark intake = RobotMap.intake;
	
	
	AHRS navx = RobotMap.navx;
	Compressor comp = RobotMap.compressor;
	
	DriverStation driverStation = DriverStation.getInstance();
	
	BuiltInAccelerometer bia = new BuiltInAccelerometer();

	Timer timer = new Timer();

	public RobotDataLoggerDataProvider() {
		super();
		timer.reset();
		timer.start();
	}

	@Override
	public String[] fetchNames() {
		return new String[] { //
				"robotMode", // RobotMode as Enum
				"robotModeInt", // RobotMode as Int

				"batteryVoltage", // Battery voltage in Volts
				"pdp.totalCurrent", // Total robot current draw
				"pdp.totalPower", // Total robot power in Watts
				"pdp.totalEnergy", // Total robot energy in Joules TODO: Do we need joules?

				"drive.l1.power", // Left1 motor power in Watts
				"drive.l2.power", // Left2 motor power in Watts
				"drive.r1.power", // Right1 motor power in Watts
				"drive.r2.power", // Right2 motor power in Watts

				"drive.l1.current", // Left1 motor current in Amps
				"drive.l2.current", // Left2 motor current in Amps
				"drive.r1.current", // Right1 motor current in Amps
				"drive.r2.current", // Right2 motor current in Amps
				
				"drive.l1.error", // Left PID error
				"drive.l2.error", // TODO: Should be the same as l1, as l2 is a follower to l1, possible removal
				"drive.r1.error", // Right PID error
				"drive.r2.error", // TODO: Should be the same as r1, as r2 is a follower to r1, possible removal
				
				"drive.l1.mode", // Left Drive Mode
				"drive.l2.mode", // TODO: Should be the same as l1, as l2 is a follower to l1, possible removal
				"drive.r1.mode", // Right Drive Mode
				"drive.r2.mode", // TODO: Should be the same as r1, as r2 is a follower to r1, possible removal
				
				"shooterMotor1.error", // Shooter PID Error
				"shooterMotor1.current", // Shooter1 current in Amps
				"shooterMotor1.voltage", // Shooter1 voltage in Volts
				"shooterMotor1.mode", // Shooter Mode

				"shooterMotor2.error", // TODO: Should be the same as sm1, as sm2 is a follower to sm1, possible removal
				"shooterMotor2.current", // Shooter2 current in Amps
				"shooterMotor2.voltage", // Shooter2 voltage in Volts
				"shooterMotor2.mode", // TODO: Should be the same as sm1, as sm2 is a follower to sm1, possible removal

				"winch.current", // Winch current in Amps
				"winch.voltage", // Winch voltage in Volts
				"winch.mode", // Winch Mode
				
				"turntable.current", // Turntable current in Amps
				"turntable.voltage", // Turntable voltage in Volts
				"turnable.mode", // Turntable Mode
				
				"intake.current", // Intake current in Amps VIA PDP
				// "intake.voltage", // Intake voltage in Volts VIA PDP TODO: Can we get volts from PDP per channel?
				
				"navx.angle", //
				"navx.pitchangle", //
				"navx.rollangle", //

                "navx.accel.x", //
                "navx.accel.y", //
                "navx.accel.z", //

                "bi.accel.x", //
                "bi.accel.y", //
                "bi.accel.z", //

		};
	}

	@Override
	public Object[] fetchData() {
		boolean shouldSample = true;
		if (Robot.getCurrentRobotMode() != RobotMode.TELEOP
				&& Robot.getCurrentRobotMode() != RobotMode.AUTONOMOUS) {
			shouldSample = timer.hasPeriodPassed(1.0);
		}

		if (!shouldSample)
			return null;

		return new Object[] { //
				Robot.currentRobotMode.toString(), //
				Robot.currentRobotMode.ordinal(), //

				f2(driverStation.getBatteryVoltage()), //
				f2(powerDistributionPanel.getTotalCurrent()), //
				f2(powerDistributionPanel.getTotalPower()), //
				f2(powerDistributionPanel.getTotalEnergy()), //

				f2(left1.getOutputVoltage() * left1.getOutputCurrent()), //
				f2(left2.getOutputVoltage() * left2.getOutputCurrent()), //
				f2(right1.getOutputVoltage() * right1.getOutputCurrent()), //
				f2(right2.getOutputVoltage() * right2.getOutputCurrent()), //

				f2(left1.getOutputCurrent()), //
				f2(left2.getOutputCurrent()), //
				f2(right1.getOutputCurrent()), //
				f2(right2.getOutputCurrent()), //
				
				f2(left1.getClosedLoopError()), // Left PID error
				f2(left2.getClosedLoopError()), // TODO: Should be the same as l1, as l2 is a follower to l1, possible removal
				f2(right1.getClosedLoopError()), // Right PID error
				f2(right2.getClosedLoopError()), // TODO: Should be the same as r1, as r2 is a follower to r1, possible removal
				
				left1.getControlMode().toString(), // Left Drive Mode
				left2.getControlMode()), // TODO: Should be the same as l1, as l2 is a follower to l1, possible removal
				right1.getControlMode()), // Right Drive Mode
				right2.getControlMode()), // TODO: Should be the same as r1, as r2 is a follower to r1, possible removal
				
				f2(shooterMotor1.getClosedLoopError()), // Shooter PID Error
				f2(shooterMotor1.getOutputCurrent()), // Shooter1 current in Amps
				f2(shooterMotor1.getOutputVoltage()), // Shooter1 voltage in Volts
				shooterMotor1.getControlMode().toString(), // Shooter Mode

				f2(shooterMotor2.getClosedLoopError()), // TODO: Should be the same as sm1, as sm2 is a follower to sm1, possible removal
				f2(shooterMotor2.getOutputCurrent()), // Shooter2 current in Amps
				f2(shooterMotor2.getOutputVoltage()), // Shooter2 voltage in Volts
				shooterMotor2.getControlMode().toString(), // TODO: Should be the same as sm1, as sm2 is a follower to sm1, possible removal

				f2(winch.getOutputCurrent()), // Winch current in Amps
				f2(winch.getOutputVoltage()), // Winch voltage in Volts
				winch.getControlMode().toString(), // Winch Mode
				
				f2(turntable.getOutputCurrent()), // Turntable current in Amps
				f2(turntable.getOutputVoltage()), // Turntable voltage in Volts
				turntable.getControlMode().toString(), // Turntable Mode
				
				f2(powerDistributionPanel.getCurrent(5)), // Intake current in Amps VIA PDP Channel 5
				// "intake.voltage", // Intake voltage in Volts VIA PDP TODO: Can we get volts from PDP per channel?
				
				f2(navx.getAngle()), //
		        f2(navx.getPitch()), //
				f2(navx.getRoll()), //

                f2(navx.getWorldLinearAccelX()), //
                f2(navx.getWorldLinearAccelY()), //
                f2(navx.getRawAccelZ()), //

                f2(bia.getX()), //
                f2(bia.getY()), //
                f2(bia.getZ()), //

				f2(shooterMotor1.getClosedLoopError()), //
				f2(shooterMotor1.getOutputCurrent()), //
				f2(shooterMotor1.getOutputVoltage()), //
				shooterMotor1.getControlMode().toString(), //

				f2(shooterMotor2.getClosedLoopError()), //
				f2(shooterMotor2.getOutputCurrent()), //
				f2(shooterMotor2.getOutputVoltage()), //
				shooterMotor2.getControlMode().toString(), //

				f2(navx.getAngle()), //
				f2(navx.getPitch()), //
				f2(navx.getRoll()), //

                f2(navx.getRawAccelX()), //
                f2(navx.getRawAccelY()), //
                f2(navx.getRawAccelZ()), //

                f2(builtinAccelerometer.getX()), //
                f2(builtinAccelerometer.getY()), //
                f2(builtinAccelerometer.getZ()), //

		};
	}

	DecimalFormat f2Formatter = new DecimalFormat("#.##");

	String f2(double f) {
		String rv = f2Formatter.format(f);
		return rv;
	}

}
