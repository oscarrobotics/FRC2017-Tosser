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
	
	AHRS navx = RobotMap.navx;
	Compressor comp = RobotMap.compressor;
	
	DriverStation driverStation = DriverStation.getInstance();
	
	BuiltInAccelerometer builtinAccelerometer = new BuiltInAccelerometer();

	Timer timer = new Timer();

	public RobotDataLoggerDataProvider() {
		super();
		timer.reset();
		timer.start();
	}

	@Override
	public String[] fetchNames() {
		return new String[] { //
				"robotMode", //
				"robotModeInt", //

				"batteryVoltage", //
				"pdp.totalCurrent", //
				"pdp.totalPower", //
				"pdp.totalEnergy", //

				"drive.l1.power", //
				"drive.l2.power", //
				"drive.r1.power", //
				"drive.r2.power", //

				"drive.l1.current", //
				"drive.l2.current", //
				"drive.r1.current", //
				"drive.r2.current", //
				
				"drive.l1.error", //
				"drive.l2.error", //
				"drive.r1.error", //
				"drive.r2.error", //
				
				"drive.l1.mode", //
				"drive.l2.mode", //
				"drive.r1.mode", //
				"drive.r2.mode", //
				
				"shooterMotor1.error", //
				"shooterMotor1.current", //
				"shooterMotor1.voltage", //
				"shooterMotor1.mode", //

				"shooterMotor2.error", //
				"shooterMotor2.current", //
				"shooterMotor2.voltage", //
				"shooterMotor2.mode", //]

				"shooterMotor1.error", //
				"shooterMotor1.current", //
				"shooterMotor1.voltage", //
				"shooterMotor1.mode", //
				
				"drive.automaticHeading", //
				"drive.angle", //
				"drive.pitchangle", //
				"drive.rollangle", //

                "drive.accel.x", //
                "drive.accel.y", //
                "drive.accel.z", //

                "bi.accel.x", //
                "bi.accel.y", //
                "bi.accel.z", //
                
                "lift.power",
                "lift.current",

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

				f2(RobotMap.left1.get()), //
				f2(RobotMap.left2.get()), //
				f2(RobotMap.right1.get()), //
				f2(RobotMap.right2.get()), //

				f2(powerDistributionPanel.getCurrent(12)), //
				f2(powerDistributionPanel.getCurrent(13)), //
				f2(powerDistributionPanel.getCurrent(14)), //
				f2(powerDistributionPanel.getCurrent(15)), //

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
                
				f2(powerDistributionPanel.getCurrent(0)), //

		};
	}

	DecimalFormat f2Formatter = new DecimalFormat("#.##");

	String f2(double f) {
		String rv = f2Formatter.format(f);
		return rv;
	}

}
