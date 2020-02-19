package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AngleAdjustSetPosition;
import frc.robot.commands.ClimberSetPercentOutput;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.commands.FeederWheelsSetPercentOutput;
import frc.robot.commands.HopperSetPercentOutput;
import frc.robot.commands.HopperSpinToRPM;
import frc.robot.commands.IntakeMotorsSetPercentOutput;
import frc.robot.commands.IntakePistonsSetState;
import frc.robot.commands.LimelightDriversMode;
import frc.robot.commands.PanelSpinnerMotorSetPercentOutput;
import frc.robot.commands.PanelSpinnerPistonSetState;
import frc.robot.commands.RampSetState;
import frc.robot.commands.ShooterSetPercentOutput;
import frc.robot.commands.ShooterSpinToRPM;
import frc.robot.commands.TurretSetPercentOutput;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.IntakePistons;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.PanelSpinnerMotor;
import frc.robot.subsystems.PanelSpinnerPiston;
import frc.robot.subsystems.PneumaticController;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {
	private XboxController xbox = new XboxController(0);
	private Joystick joystick = new Joystick(1);
	private Joystick board = new Joystick(2);

	private final AngleAdjust mAngleAdjust = new AngleAdjust();
	private final Climber mClimber = new Climber();
	private final Drivetrain mDrivetrain = new Drivetrain();
	private final FeederWheels mFeederWheels = new FeederWheels();
	private final Hopper mHopper = new Hopper();
	private final IntakeMotors mIntakeMotors = new IntakeMotors();
	private final IntakePistons mIntakePistons = new IntakePistons();
	private final Limelight mLimelight = new Limelight();
	private final PanelSpinnerMotor mPanelSpinnerMotor = new PanelSpinnerMotor();
	private final PanelSpinnerPiston mPanelSpinnerPiston = new PanelSpinnerPiston();
	private final PneumaticController mPneumaticController = new PneumaticController();
	private final Ramp mRamp = new Ramp();
	private final Shooter mShooter = new Shooter();
	private final Turret mTurret = new Turret();

	public RobotContainer() {
		CommandScheduler.getInstance().setDefaultCommand(mAngleAdjust, new AngleAdjustSetPosition(mAngleAdjust, 1.0));
		CommandScheduler.getInstance().setDefaultCommand(mClimber, new ClimberSetPercentOutput(mClimber, mPneumaticController, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mDrivetrain, new DrivetrainArcadeDrive(mDrivetrain, mPneumaticController, xbox));
		CommandScheduler.getInstance().setDefaultCommand(mFeederWheels, new FeederWheelsSetPercentOutput(mFeederWheels, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mHopper, new HopperSetPercentOutput(mHopper, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mIntakeMotors, new IntakeMotorsSetPercentOutput(mIntakeMotors, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mIntakePistons, new IntakePistonsSetState(mIntakePistons, true));
		CommandScheduler.getInstance().setDefaultCommand(mLimelight, new LimelightDriversMode(mLimelight));
		CommandScheduler.getInstance().setDefaultCommand(mPanelSpinnerMotor, new PanelSpinnerMotorSetPercentOutput(mPanelSpinnerMotor, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mPanelSpinnerPiston, new PanelSpinnerPistonSetState(mPanelSpinnerPiston, false));
		CommandScheduler.getInstance().setDefaultCommand(mShooter, new ShooterSetPercentOutput(mShooter, mPneumaticController, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mTurret, new TurretSetPercentOutput(mTurret, joystick));
		CommandScheduler.getInstance().setDefaultCommand(mRamp, new RampSetState(mRamp, false));

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftBumper, xButtonRightBumper, xButtonLeftStick,
				xButtonRightStick;
		JoystickButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9,
				jButton10, jButton11, jButton12;
		JoystickButton bButton1, bButton2, bButton3, bButton4, bButton5, bButton6, bButton7, bButton8, bButton9,
				bButton10, bButton11;

		xButtonA = new JoystickButton(xbox, 1);
		xButtonB = new JoystickButton(xbox, 2);
		xButtonX = new JoystickButton(xbox, 3);
		xButtonY = new JoystickButton(xbox, 4);
		xButtonLeftBumper = new JoystickButton(xbox, 5);
		xButtonRightBumper = new JoystickButton(xbox, 6);
		xButtonLeftStick = new JoystickButton(xbox, 9);
		xButtonRightStick = new JoystickButton(xbox, 10);

		jButton1 = new JoystickButton(joystick, 1);
		jButton2 = new JoystickButton(joystick, 2);
		jButton3 = new JoystickButton(joystick, 3);
		jButton4 = new JoystickButton(joystick, 4);
		jButton5 = new JoystickButton(joystick, 5);
		jButton6 = new JoystickButton(joystick, 6);
		jButton7 = new JoystickButton(joystick, 7);
		jButton8 = new JoystickButton(joystick, 8);
		jButton9 = new JoystickButton(joystick, 9);
		jButton10 = new JoystickButton(joystick, 10);
		jButton11 = new JoystickButton(joystick, 11);
		jButton12 = new JoystickButton(joystick, 12);

		bButton1 = new JoystickButton(board, 1);
		bButton2 = new JoystickButton(board, 2);
		bButton3 = new JoystickButton(board, 3);
		bButton4 = new JoystickButton(board, 4);
		bButton5 = new JoystickButton(board, 5);
		bButton6 = new JoystickButton(board, 6);
		bButton7 = new JoystickButton(board, 7);
		bButton8 = new JoystickButton(board, 8);
		bButton9 = new JoystickButton(board, 9);
		bButton10 = new JoystickButton(board, 10);
		bButton11 = new JoystickButton(board, 11);

		jButton1.whileHeld(new ShooterSpinToRPM(mShooter, mPneumaticController, 4250.0));
		jButton1.whileHeld(new FeederWheelsSetPercentOutput(mFeederWheels, 0.7));
		jButton2.whileHeld(new RampSetState(mRamp, true));
		jButton3.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, -1.0));
		jButton4.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, 1.0));
		jButton5.whenPressed(new IntakePistonsSetState(mIntakePistons, true));
		jButton6.whenPressed(new IntakePistonsSetState(mIntakePistons, false));
		jButton7.whileHeld(new HopperSpinToRPM(mHopper, 40.0)); //.3 for indexing
		jButton8.whileHeld(new HopperSetPercentOutput(mHopper, -1.0));
	}

	public XboxController getController() {
		return xbox;
	}

	public Joystick getJoystick() {
		return joystick;
	}

	public Joystick getBoard() {
		return board;
	}

	public Command getAutonomousCommand() {
		TrajectoryConfig config = new TrajectoryConfig(mDrivetrain.getMaxVelocityMetersPerSecond(),
				mDrivetrain.getMaxAcellMetersPerSecondPerSecond()); // TODO this will have to be reworked into it's
																	// seperate file like in 2018 and selected auto
																	// command will come from a Sendable Chooser

		config.setKinematics(mDrivetrain.getKinematics());

		Trajectory trajectory = TrajectoryGenerator
				.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);

		RamseteCommand command = new RamseteCommand(trajectory, mDrivetrain::getPose, new RamseteController(2.0, 0.7),
				mDrivetrain.getFeedForward(), mDrivetrain.getKinematics(), mDrivetrain::getSpeeds,
				mDrivetrain.getLeftPIDController(), mDrivetrain.getRightPIDController(), mDrivetrain::setOutput,
				mDrivetrain);

		return command;
	}
}
