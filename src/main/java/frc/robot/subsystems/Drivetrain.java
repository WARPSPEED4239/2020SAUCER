package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final double RAMP_RATE = 0.2;
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 40.0, 60.0, 1.0); //TODO If not working get rid of this?

  private double[] ypr = new double[3];

  private final double TRACK_WIDTH_METERS = Units.inchesToMeters(24.88);
  private final double WHEEL_DIAMETER = 6.0;
  private final double GEARBOX_RATIO = 7.08;
  private final int ENCODER_TICKS_PER_REV = 2048;

  private final double kS = 0.268; //TODO TUNE THESE WITH frc-characterization drive new
  private final double kV = 1.89;
  private final double kA = 0.243;
  private final double kP = 9.95;
  private final double kI = 0.0;
  private final double kD = 0.0;
  private final double maxVelocityMetersPerSecond = Units.feetToMeters(19.0);
  private final double maxAcellMetersPerSecondPerSecond = Units.feetToMeters(6.0);

  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_ONE);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_TWO);
  private WPI_TalonFX leftMotor3 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_THREE);
  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_ONE);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_TWO);
  private WPI_TalonFX rightMotor3 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_THREE);
  
  private DoubleSolenoid shifter = new DoubleSolenoid(Constants.DRIVETRAIN_SHIFTING_SOLENOID_FORWARD, Constants.DRIVETRAIN_SHIFTING_SOLENOID_REVERSE);
  private PigeonIMU IMU = new PigeonIMU(Constants.PIGEON_IMU);

  private DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1);
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(TRACK_WIDTH_METERS); 
  private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-getIMUYaw())); //TODO REVIEW: Could not pass in kinematics as an argument like in tutorial

  private Pose2d pose;
  private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV, kA);

  private PIDController leftPIDController = new PIDController(kP, kI, kD);
  private PIDController rightPIDController = new PIDController(kP, kI, kD);

  public Drivetrain() {
    leftMotor1.configFactoryDefault();
    leftMotor2.configFactoryDefault();
    leftMotor3.configFactoryDefault();
    rightMotor1.configFactoryDefault();
    rightMotor2.configFactoryDefault();
    rightMotor3.configFactoryDefault();

    leftMotor1.setInverted(false);
    leftMotor2.setInverted(false);
    leftMotor3.setInverted(false);
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    rightMotor3.setInverted(false);

    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    leftMotor3.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor3.setNeutralMode(NeutralMode.Brake);

    leftMotor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    leftMotor2.configSupplyCurrentLimit(CURRENT_LIMIT);
    leftMotor3.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor2.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor3.configSupplyCurrentLimit(CURRENT_LIMIT);

    leftMotor1.configOpenloopRamp(RAMP_RATE);
    leftMotor2.configOpenloopRamp(RAMP_RATE);
    leftMotor3.configOpenloopRamp(RAMP_RATE);
    rightMotor1.configOpenloopRamp(RAMP_RATE);
    rightMotor2.configOpenloopRamp(RAMP_RATE);
    rightMotor3.configOpenloopRamp(RAMP_RATE);
    
    leftMotor2.follow(leftMotor1);
    leftMotor3.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);
    rightMotor3.follow(rightMotor1);

    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    leftMotor1.setSensorPhase(false);
    rightMotor1.setSensorPhase(true);
  }
  
  @Override
  public void periodic() {
    pose = odometry.update(Rotation2d.fromDegrees(-getIMUYaw()), getLeftDistanceMeters(), getRightDistanceMeters()); //TODO Could not pass in getSpeeds like in video
  }

  public void arcadeDrive(double move, double rotate) {
    final double MIN_MOVE_THRESHOLD = 0.07;
    final double MIN_ROTATE_THRESHOLD = 0.07;

    if (Math.abs(move) < MIN_MOVE_THRESHOLD)
      move = 0.0;

    if (Math.abs(rotate) < MIN_ROTATE_THRESHOLD)
      rotate = 0.0;
    
    drive.arcadeDrive(move, rotate);
  }

  public void setHighGear() {
    shifter.set(Value.kForward);
  }

  public void setLowGear() {
    shifter.set(Value.kReverse);
  }

  public void resetEncoders() {
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);
  }

  public double getMaxVelocityMetersPerSecond() {
    return maxVelocityMetersPerSecond;
  }

  public double getMaxAcellMetersPerSecondPerSecond () {
    return maxAcellMetersPerSecondPerSecond;
  }

  public double getIMUYaw() {
    IMU.getYawPitchRoll(ypr);
    return ypr[0];
  }

  public double getLeftDistanceMeters() {
    return leftMotor1.getSelectedSensorPosition() / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER);
  }

  public double getRightDistanceMeters() {
    return rightMotor1.getSelectedSensorVelocity() / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER);
  }

  public SimpleMotorFeedforward getFeedForward() {
    return feedforward;
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public Pose2d getPose() {
    return pose;
  }

  public PIDController getLeftPIDController() {
    return leftPIDController;
  }

  public PIDController getRightPIDController() {
    return rightPIDController;
  }

  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(
      leftMotor1.getSelectedSensorVelocity() * 10.0 / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER), //TODO Review this math, converting from Counts per .1 secs to Meters/sec
      rightMotor1.getSelectedSensorVelocity() * 10.0 / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER)
      );
  }

  public void setOutput(double leftVolts, double rightVolts) {
    leftMotor1.set(leftVolts / 12.0);
    rightMotor1.set(rightVolts / 12.0);
  }
}
