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
import frc.robot.commands.AngleAdjustSetPositionWithJoystick;
import frc.robot.commands.ClimberSetPercentOutput;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.commands.DrivetrainShiftingSetState;
import frc.robot.commands.FeederWheelsSetPercentOutput;
import frc.robot.commands.FeederWheelsSpinToRPM;
import frc.robot.commands.HopperSetPercentOutput;
import frc.robot.commands.HopperSpinToRPM;
import frc.robot.commands.IntakeMotorsSetPercentOutput;
import frc.robot.commands.IntakePistonsSetState;
import frc.robot.commands.LimelightDriversMode;
import frc.robot.commands.PanelSpinnerMotorSetPercentOutput;
import frc.robot.commands.PanelSpinnerPistonSetState;
import frc.robot.commands.PanelSpinnerSpinForRotations;
import frc.robot.commands.PanelSpinnerSpinToColor;
import frc.robot.commands.RampSetState;
import frc.robot.commands.ShooterSetPercentOutput;
import frc.robot.commands.ShooterSpinToRPM;
import frc.robot.commands.TurretSetPercentOutputWithJoystick;
import frc.robot.commands.automated.VisionTracking;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
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
	private XboxController mXbox = new XboxController(0);
	private Joystick mJoystick = new Joystick(1);

	private final AngleAdjust mAngleAdjust = new AngleAdjust();
	private final Climber mClimber = new Climber();
	private final Drivetrain mDrivetrain = new Drivetrain();
	private final DrivetrainShifting mDrivetrainShifting = new DrivetrainShifting();
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
		CommandScheduler.getInstance().setDefaultCommand(mAngleAdjust, new AngleAdjustSetPositionWithJoystick(mAngleAdjust, mJoystick));
		CommandScheduler.getInstance().setDefaultCommand(mClimber, new ClimberSetPercentOutput(mClimber, mPneumaticController, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mDrivetrain, new DrivetrainArcadeDrive(mDrivetrain, mPneumaticController, mXbox));
		CommandScheduler.getInstance().setDefaultCommand(mDrivetrainShifting, new DrivetrainShiftingSetState(mDrivetrainShifting, true));
		CommandScheduler.getInstance().setDefaultCommand(mFeederWheels, new FeederWheelsSetPercentOutput(mFeederWheels, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mHopper, new HopperSetPercentOutput(mHopper, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mIntakeMotors, new IntakeMotorsSetPercentOutput(mIntakeMotors, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mIntakePistons, new IntakePistonsSetState(mIntakePistons, true));
		CommandScheduler.getInstance().setDefaultCommand(mLimelight, new LimelightDriversMode(mLimelight));
		CommandScheduler.getInstance().setDefaultCommand(mPanelSpinnerMotor, new PanelSpinnerMotorSetPercentOutput(mPanelSpinnerMotor, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mPanelSpinnerPiston, new PanelSpinnerPistonSetState(mPanelSpinnerPiston, false));
		CommandScheduler.getInstance().setDefaultCommand(mShooter, new ShooterSetPercentOutput(mShooter, mPneumaticController, 0.0));
		CommandScheduler.getInstance().setDefaultCommand(mTurret, new TurretSetPercentOutputWithJoystick(mTurret, mJoystick));
		CommandScheduler.getInstance().setDefaultCommand(mRamp, new RampSetState(mRamp, false));

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftBumper, xButtonRightBumper, xButtonLeftStick,
				xButtonRightStick;
		JoystickButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9,
				jButton10, jButton11, jButton12;

		xButtonA = new JoystickButton(mXbox, 1);
		xButtonB = new JoystickButton(mXbox, 2);
		xButtonX = new JoystickButton(mXbox, 3);
		xButtonY = new JoystickButton(mXbox, 4);
		xButtonLeftBumper = new JoystickButton(mXbox, 5);
		xButtonRightBumper = new JoystickButton(mXbox, 6);
		xButtonLeftStick = new JoystickButton(mXbox, 9);
		xButtonRightStick = new JoystickButton(mXbox, 10);

		jButton1 = new JoystickButton(mJoystick, 1);
		jButton2 = new JoystickButton(mJoystick, 2);
		jButton3 = new JoystickButton(mJoystick, 3);
		jButton4 = new JoystickButton(mJoystick, 4);
		jButton5 = new JoystickButton(mJoystick, 5);
		jButton6 = new JoystickButton(mJoystick, 6);
		jButton7 = new JoystickButton(mJoystick, 7);
		jButton8 = new JoystickButton(mJoystick, 8);
		jButton9 = new JoystickButton(mJoystick, 9);
		jButton10 = new JoystickButton(mJoystick, 10);
		jButton11 = new JoystickButton(mJoystick, 11);
		jButton12 = new JoystickButton(mJoystick, 12);

		xButtonA.whenPressed(new DrivetrainShiftingSetState(mDrivetrainShifting, true));
		xButtonB.whenPressed(new DrivetrainShiftingSetState(mDrivetrainShifting, false));

		//jButton1.whileHeld(new ShootingRoutine(mFeederWheels, mHopper, mRamp, mShooter, 3000.0, 40.0, 4250.0));
		jButton1.whileHeld(new ShooterSpinToRPM(mShooter, mPneumaticController, 4250.0));
		jButton1.whileHeld(new FeederWheelsSpinToRPM(mFeederWheels, 3000.0));

		jButton2.whileHeld(new VisionTracking(mAngleAdjust, mLimelight, mTurret, mJoystick));

		jButton3.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, -1.0));
		jButton3.whileHeld(new HopperSpinToRPM(mHopper, 30.0));

		jButton4.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, 1.0));

		jButton5.toggleWhenPressed(new IntakePistonsSetState(mIntakePistons, false));
		jButton6.toggleWhenPressed(new PanelSpinnerPistonSetState(mPanelSpinnerPiston, true));

		jButton7.whileHeld(new HopperSetPercentOutput(mHopper, 0.3));
		jButton8.whileHeld(new HopperSetPercentOutput(mHopper, -1.0));

		jButton9.whileHeld(new ClimberSetPercentOutput(mClimber, mPneumaticController, -1.0)); //CLIMBING
		jButton9.whenPressed(new DrivetrainShiftingSetState(mDrivetrainShifting, false)); //LOW GEAR

		jButton10.whileHeld(new ClimberSetPercentOutput(mClimber, mPneumaticController, 1.0)); //RAISING
		jButton10.whileHeld(new DrivetrainShiftingSetState(mDrivetrainShifting, true)); //HIGH GEAR

		jButton11.whenPressed(new PanelSpinnerSpinForRotations(mPanelSpinnerMotor, 3.5));
		jButton12.whenPressed(new PanelSpinnerSpinToColor(mPanelSpinnerMotor));
	}

	public XboxController getController() {
		return mXbox;
	}

	public Joystick getJoystick() {
		return mJoystick;
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
