package org.usfirst.frc.team832.robot;

import com.ctre.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

//	public static AHRS navx;
//	public static SerialPort.Port navXSerialPort = SerialPort.Port.kUSB;


	// shooter
	public static int shooterMotor1ID = 7;
	public static int shooterMotor2ID = 8;
	public static CANTalon shooterMotor1;
	public static CANTalon shooterMotor2;

	// drivetrain
	public static int left1ID = 1;
	public static int left2ID = 2;
	public static int right1ID = 3;
	public static int right2ID = 4;
	public static CANTalon left1;
	public static CANTalon left2;
	public static CANTalon right1;
	public static CANTalon right2;
	public static RobotDrive westCoast;

	// winch
	public static final int winchPWMPort = 0;
	public static Spark bigWinch;

	// turntable
	public static final int turntableID = 5;
	public static CANTalon turnTable;

	// intake
	public static final int intakeID = 6;
	public static CANTalon collectorRoller;

	// pneumatics
	public static final int pcmID = 9;
	public static Compressor compressor;
	public static DoubleSolenoid gearShiftSol;
	public static Solenoid winchTiltSol;
	public static DoubleSolenoid ballDoorSol;

	// electronics
	public static int pdpID = 0;
	public static PowerDistributionPanel powerDP;
	
	public static void init() {

		// inits navX IMU
//		try {
//			navx = new AHRS(navXSerialPort);
//		} catch (RuntimeException ex) {
//			DriverStation.reportError("Error instantiating navX-Micro:  " + ex.getMessage(), true);
//		}
		
		// pdp
		powerDP = new PowerDistributionPanel(0);
		powerDP.clearStickyFaults();
		
		// shooter
		shooterMotor1 = new CANTalon(7);
		shooterMotor2 = new CANTalon(8);
		
		shooterMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterMotor1.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotor1.setAllowableClosedLoopErr(50);
		
		shooterMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterMotor2.set(shooterMotor1.getDeviceID());
		shooterMotor1.clearStickyFaults();
		shooterMotor2.clearStickyFaults();
		
		//drivetrain
		left1 = new CANTalon(1);
		left2 = new CANTalon(2);
		right1 = new CANTalon(3);
		right2 = new CANTalon(4);
		
		
		left2.changeControlMode(CANTalon.TalonControlMode.Follower);
		right2.changeControlMode(CANTalon.TalonControlMode.Follower);
		left2.set(left1.getDeviceID());
		right2.set(right1.getDeviceID());
		
		left1.clearStickyFaults();
		left2.clearStickyFaults();
		right1.clearStickyFaults();
		right2.clearStickyFaults();
		
		westCoast = new RobotDrive(left1, right1);

		// intake
		collectorRoller = new CANTalon(6);
		collectorRoller.clearStickyFaults();
		
		// turntable
		turnTable = new CANTalon(5);
		turnTable.clearStickyFaults();
		
		// pneumatics
		compressor = new Compressor(9);
		compressor.setClosedLoopControl(true);
		gearShiftSol = new DoubleSolenoid(9,1, 0);
		winchTiltSol = new Solenoid(9,2);
		ballDoorSol = new DoubleSolenoid(9, 3, 4);

		// winch
		bigWinch = new Spark(0);

	}
}
