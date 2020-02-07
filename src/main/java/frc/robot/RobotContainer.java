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
import frc.robot.commands.ClimberSetPercentOutput;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.IntakeMotorsSetPercentOutput;
import frc.robot.commands.IntakePistonsSetState;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.IntakePistons;
import frc.robot.subsystems.PanelSpinnerMotor;
import frc.robot.subsystems.PanelSpinnerPiston;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterAngleAdjust;
import frc.robot.subsystems.Turret;

public class RobotContainer {
	private XboxController xbox = new XboxController(0);
	private Joystick joystick = new Joystick(1);
	private Joystick board = new Joystick(2);

	private final Climber mClimber = new Climber();
	private final Drivetrain mDrivetrain = new Drivetrain();
	private final Elevator mElevator = new Elevator();
	private final FeederWheels mFeederWheels = new FeederWheels();
	private final Hopper mHopper = new Hopper();
	private final IntakeMotors mIntakeMotors = new IntakeMotors();
	private final IntakePistons mIntakePistons = new IntakePistons();
	private final PanelSpinnerMotor mPanelSpinner = new PanelSpinnerMotor();
	private final PanelSpinnerPiston mPanelSpinnerPiston = new PanelSpinnerPiston();
	private final Ramp mRamp = new Ramp();
	private final Shooter mShooter = new Shooter();
	private final ShooterAngleAdjust mShooterAngleAdjust = new ShooterAngleAdjust();
	private final Turret mTurret = new Turret();

	private final ClimberSetPercentOutput mClimberSetPercentOutput = new ClimberSetPercentOutput(mClimber, 0.0); //TODO Is this neccessary?
	private final DrivetrainArcadeDrive mDrivetrainArcadeDrive = new DrivetrainArcadeDrive(mDrivetrain, xbox);
	private final ElevatorSetPercentOutput mElevatorSetPercentOutput = new ElevatorSetPercentOutput(mElevator, 0.0);
	private final IntakeMotorsSetPercentOutput mIntakeSetPercentOutput = new IntakeMotorsSetPercentOutput(mIntakeMotors, 0.0);
	private final IntakePistonsSetState mIntakePistonsSetState = new IntakePistonsSetState(mIntakePistons, true);

	public RobotContainer() {

		CommandScheduler.getInstance().setDefaultCommand(mClimber, mClimberSetPercentOutput);
		CommandScheduler.getInstance().setDefaultCommand(mDrivetrain, mDrivetrainArcadeDrive);
		CommandScheduler.getInstance().setDefaultCommand(mElevator, mElevatorSetPercentOutput);
		CommandScheduler.getInstance().setDefaultCommand(mIntakeMotors, mIntakeSetPercentOutput);
		CommandScheduler.getInstance().setDefaultCommand(mIntakePistons, mIntakePistonsSetState);

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

		jButton9.whileHeld(new ElevatorSetPercentOutput(mElevator, -1.0));
		jButton10.whileHeld(new ElevatorSetPercentOutput(mElevator, 1.0));
		jButton11.whileHeld(new ClimberSetPercentOutput(mClimber, -1.0));
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

	public Command getAutonomousCommand() { // TODO Does this command need to be defined above like line 33? Confused on
											// when this must be done
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
