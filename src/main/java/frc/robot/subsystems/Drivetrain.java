package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final double RAMP_RATE = 0.3;
  //private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 40.0, 60.0, 1.0); //TODO If not working get rid of this?

  private double[] ypr = new double[3];

  private final double TRACK_WIDTH_METERS = 0.74;
  private final double WHEEL_DIAMETER = 6.0;
  private final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(6.0);
  private final double GEARBOX_RATIO = 7.08;
  private final int ENCODER_TICKS_PER_REV = 2048;

  private final double kP = 0.05;// 0.464;//0.297;
  private final double kI = 0.0;
  private final double kD = 0.0;

  private final double maxVelocityMetersPerSecond = Units.feetToMeters(19.0);
  private final double maxAcellMetersPerSecondPerSecond = Units.feetToMeters(6.0);

  private double prevLeftVel;
  private double prevRightVel;
  private double prevTime;
  private final double driveMetersPerTick = (Math.PI * WHEEL_DIAMETER_METERS) / (Constants.COUNTS_PER_REVOLUTION_ENCODER * GEARBOX_RATIO);
  
  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_ONE);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_TWO);
  private WPI_TalonFX leftMotor3 = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_THREE);
  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_ONE);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_TWO);
  private WPI_TalonFX rightMotor3 = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_THREE);
  
  private PigeonIMU IMU = new PigeonIMU(Constants.PIGEON_IMU);

  private DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1);
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(TRACK_WIDTH_METERS); 
  private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getIMUYaw()));
  
  private final SimpleMotorFeedforward feedForward= new SimpleMotorFeedforward(0.419, 1.55, 0.37);
  private Pose2d pose;

  public Drivetrain() {
    leftMotor1.configFactoryDefault();
    leftMotor2.configFactoryDefault();
    leftMotor3.configFactoryDefault();
    rightMotor1.configFactoryDefault();
    rightMotor2.configFactoryDefault();
    rightMotor3.configFactoryDefault();

    leftMotor2.follow(leftMotor1);
    leftMotor3.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);
    rightMotor3.follow(rightMotor1);

    leftMotor1.setInverted(true);
    leftMotor2.setInverted(InvertType.FollowMaster);
    leftMotor3.setInverted(InvertType.FollowMaster);
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(InvertType.FollowMaster);
    rightMotor3.setInverted(InvertType.FollowMaster);
  
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    leftMotor3.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor3.setNeutralMode(NeutralMode.Brake);

    /*leftMotor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    leftMotor2.configSupplyCurrentLimit(CURRENT_LIMIT);
    leftMotor3.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor2.configSupplyCurrentLimit(CURRENT_LIMIT);
    rightMotor3.configSupplyCurrentLimit(CURRENT_LIMIT);*/

    leftMotor1.configOpenloopRamp(RAMP_RATE);
    leftMotor2.configOpenloopRamp(RAMP_RATE);
    leftMotor3.configOpenloopRamp(RAMP_RATE);
    rightMotor1.configOpenloopRamp(RAMP_RATE);
    rightMotor2.configOpenloopRamp(RAMP_RATE);
    rightMotor3.configOpenloopRamp(RAMP_RATE);

    leftMotor1.configVoltageCompSaturation(12.0);
    rightMotor1.configVoltageCompSaturation(12.0);

    leftMotor1.enableVoltageCompensation(true);
    rightMotor1.enableVoltageCompensation(true);

    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    leftMotor1.config_kP(0, kP, Constants.TIMEOUT_MS);
    leftMotor1.config_kI(0, kI, Constants.TIMEOUT_MS);
    leftMotor1.config_kD(0, kD, Constants.TIMEOUT_MS);
    leftMotor1.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    leftMotor1.config_IntegralZone(0, 0, Constants.TIMEOUT_MS);
    leftMotor1.selectProfileSlot(0, 0);

    rightMotor1.config_kP(0, kP, Constants.TIMEOUT_MS);
    rightMotor1.config_kI(0, kI, Constants.TIMEOUT_MS);
    rightMotor1.config_kD(0, kD, Constants.TIMEOUT_MS);
    rightMotor1.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    rightMotor1.config_IntegralZone(0, 0, Constants.TIMEOUT_MS);
    rightMotor1.selectProfileSlot(0, 0);

    IMU.setYaw(0.0);
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);

    drive.setRightSideInverted(false);
  }
  
  @Override
  public void periodic() {
    pose = odometry.update(Rotation2d.fromDegrees(getIMUYaw()), getLeftDistanceMeters(), getRightDistanceMeters());

    SmartDashboard.putNumber("Left Voltage", getLeftVoltage());
    SmartDashboard.putNumber("Right Voltage", getRightVoltage());

    SmartDashboard.putNumber("Left Distance Meters", getLeftDistanceMeters());
    SmartDashboard.putNumber("Right Distance Meters", getRightDistanceMeters());

    SmartDashboard.putNumber("Left Velocity", getLeftVelocity());
    SmartDashboard.putNumber("Right Velocity", getRightVelocity());

    SmartDashboard.putNumber("IMU Yaw", getIMUYaw());

    SmartDashboard.putNumber("Pose X", pose.getTranslation().getX());
    SmartDashboard.putNumber("Pose Y", pose.getTranslation().getY());

    double measuredLeftVel = getLeftVelocity();
    double measuredRightVel = getRightVelocity();

    SmartDashboard.putNumberArray("Left Velocities", new double[] {prevLeftVel, measuredLeftVel});
    SmartDashboard.putNumberArray("Right Velocities", new double[] {prevRightVel, measuredRightVel});
    SmartDashboard.putNumber("Left Error", prevLeftVel - measuredLeftVel);
    SmartDashboard.putNumber("Right Error", prevRightVel - measuredRightVel);
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

  public void resetEncoders() {
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);
  }

  public void resetGyro() {
    IMU.setYaw(0.0);
  }

  public void resetSensorsAndOdemetry() {
    resetEncoders();
    resetGyro();
    odometry.resetPosition(new Pose2d(), Rotation2d.fromDegrees(0.0));
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

  public double getLeftVoltage() {
    return leftMotor1.getMotorOutputVoltage();
  }

  public double getRightVoltage() {
    return rightMotor1.getMotorOutputVoltage();
  }

  public double getLeftDistanceMeters() {
    return leftMotor1.getSelectedSensorPosition() / (ENCODER_TICKS_PER_REV * GEARBOX_RATIO) * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER);
  }

  public double getRightDistanceMeters() {
    return rightMotor1.getSelectedSensorPosition() / (ENCODER_TICKS_PER_REV * GEARBOX_RATIO) * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER);
  }

  public double getLeftVelocity() {
    return leftMotor1.getSelectedSensorVelocity() / (ENCODER_TICKS_PER_REV * GEARBOX_RATIO) * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER) * 10;
  }

  public double getRightVelocity() {
    return rightMotor1.getSelectedSensorVelocity() / (ENCODER_TICKS_PER_REV * GEARBOX_RATIO) * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER) * 10;
  }

  public SimpleMotorFeedforward getFeedForward() {
    return feedForward;
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public Pose2d getPose() {
    return pose;
  }

  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(
      leftMotor1.getSelectedSensorVelocity() * 10.0 / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER),
      rightMotor1.getSelectedSensorVelocity() * 10.0 / ENCODER_TICKS_PER_REV * GEARBOX_RATIO * Math.PI * Units.inchesToMeters(WHEEL_DIAMETER)
      );
  }

  public void setOutput(double leftVolts, double rightVolts) {
    leftMotor1.set(leftVolts / 12.0);
    rightMotor1.set(rightVolts / 12.0);

    drive.feed();
  }

  public void stop() {
    setOutput(0.0, 0.0);
  }

  public void setVelocity(double leftVelocity, double rightVelocity) {
    double currentTime = Timer.getFPGATimestamp();
    double dt = currentTime - prevTime;
    double leftAccel = 0;
    double rightAccel = 0;

    if (dt > 0 && dt < 0.1) {
      leftAccel = (leftVelocity - prevLeftVel) / dt;
      rightAccel = (rightVelocity - prevRightVel) / dt;
    }

    double leftFeedForwardVolts = feedForward.calculate(leftVelocity, leftAccel);
    double rightFeedForwardVolts = feedForward.calculate(rightVelocity, rightAccel);

    leftMotor1.set(ControlMode.Velocity, leftVelocity / (driveMetersPerTick * 10.0), DemandType.ArbitraryFeedForward, leftFeedForwardVolts / 12.0);
    rightMotor1.set(ControlMode.Velocity, rightVelocity / (driveMetersPerTick * 10.0), DemandType.ArbitraryFeedForward, rightFeedForwardVolts / 12.0);
  
    prevLeftVel = leftVelocity;
    prevRightVel = rightVelocity;
    prevTime = currentTime;
  }
}
