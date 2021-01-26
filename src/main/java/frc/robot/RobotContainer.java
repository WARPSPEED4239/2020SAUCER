package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AngleAdjustSetPositionWithJoystick;
import frc.robot.commands.ClimberSetPercentOutput;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.commands.DrivetrainShiftingSetState;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.commands.FeederWheelsSetPercentOutput;
import frc.robot.commands.HopperSetPercentOutput;
import frc.robot.commands.IntakeMotorsSetPercentOutput;
import frc.robot.commands.IntakePistonsSetState;
import frc.robot.commands.LimelightDriversMode;
import frc.robot.commands.PanelSpinnerMotorSetPercentOutput;
import frc.robot.commands.PanelSpinnerPistonSetState;
import frc.robot.commands.PanelSpinnerSpinToColor;
import frc.robot.commands.PneumaticControllerCompressorSetState;
import frc.robot.commands.RampSetState;
import frc.robot.commands.ShooterSetPercentOutput;
import frc.robot.commands.TurretSetPercentOutputWithJoystick;
import frc.robot.commands.automated.VisionTracking;
import frc.robot.commands.autonomous.AutonomousCommand;
import frc.robot.commands.autonomous.SendableChoosers.StartingPosition;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
import frc.robot.subsystems.Elevator;
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
	private final Elevator mElevator = new Elevator();
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

  	private SendableChooser<StartingPosition> positionChooser = new SendableChooser<>();
  	private SendableChooser<TargetTask> targetChooser = new SendableChooser<>();

	public RobotContainer() {
		mAngleAdjust.setDefaultCommand(new AngleAdjustSetPositionWithJoystick(mAngleAdjust, mJoystick));
		mClimber.setDefaultCommand(new ClimberSetPercentOutput(mClimber, 0.0));
		mDrivetrain.setDefaultCommand(new DrivetrainArcadeDrive(mDrivetrain, mXbox));
		mDrivetrainShifting.setDefaultCommand(new DrivetrainShiftingSetState(mDrivetrainShifting, true));
		mElevator.setDefaultCommand(new ElevatorSetPercentOutput(mElevator, mJoystick));
		mFeederWheels.setDefaultCommand(new FeederWheelsSetPercentOutput(mFeederWheels, 0.0));
		mHopper.setDefaultCommand(new HopperSetPercentOutput(mHopper, 0.0));
		mIntakeMotors.setDefaultCommand(new IntakeMotorsSetPercentOutput(mIntakeMotors, 0.0));
		mIntakePistons.setDefaultCommand(new IntakePistonsSetState(mIntakePistons, false));
		mLimelight.setDefaultCommand(new LimelightDriversMode(mLimelight));
		mPanelSpinnerMotor.setDefaultCommand(new PanelSpinnerMotorSetPercentOutput(mPanelSpinnerMotor, 0.0));
		mPanelSpinnerPiston.setDefaultCommand(new PanelSpinnerPistonSetState(mPanelSpinnerPiston, false));
		mPneumaticController.setDefaultCommand(new PneumaticControllerCompressorSetState(mPneumaticController, true));
		mShooter.setDefaultCommand(new ShooterSetPercentOutput(mShooter, 0.0));
		mTurret.setDefaultCommand(new TurretSetPercentOutputWithJoystick(mTurret, mJoystick));
		mRamp.setDefaultCommand(new RampSetState(mRamp, false));

		configureButtonBindings();

		positionChooser.setDefaultOption("Left", StartingPosition.Left);
   	 	positionChooser.addOption("Center", StartingPosition.Center);
    	positionChooser.addOption("Right", StartingPosition.Right);
    	SmartDashboard.putData(positionChooser);

		targetChooser.setDefaultOption("Drive Forward", TargetTask.DriveForward);
    	targetChooser.addOption("Shoot 3", TargetTask.Shoot3);
    	targetChooser.addOption("Steal 2 Shoot 5", TargetTask.Steal2Shoot5);
    	targetChooser.addOption("Shoot 3 Grab 5 Shoot 5", TargetTask.Shoot3Grab5Shoot5);
    	targetChooser.addOption("Shoot 3 Grab 3 Shoot 3", TargetTask.Shoot3Grab3Shoot3);
    	targetChooser.addOption("Do Nothing", TargetTask.DoNothing);
		SmartDashboard.putData(targetChooser);
		
		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
    	cam0.setFPS(10);

		var voltageConstraint = new DifferentialDriveVoltageConstraint(mDrivetrain.getFeedForward(), mDrivetrain.getKinematics(), 9.0);
		TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(6.0), Units.feetToMeters(3.0)).setKinematics(mDrivetrain.getKinematics()).addConstraint(voltageConstraint);
		config.setKinematics(mDrivetrain.getKinematics());
      	Trajectories.initialize(config);
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

		xButtonA.whenPressed(new DrivetrainShiftingSetState(mDrivetrainShifting, false));
		xButtonB.whenPressed(new DrivetrainShiftingSetState(mDrivetrainShifting, true));

		//jButton1.whileHeld(new ShootingRoutine(mFeederWheels, mHopper, mRamp, mShooter, 3000.0, 40.0, 4250.0));
		jButton1.whileHeld(new ShooterSetPercentOutput(mShooter, 0.8));
		jButton1.whileHeld(new FeederWheelsSetPercentOutput(mFeederWheels, 0.75));
		jButton1.whileHeld(new PneumaticControllerCompressorSetState(mPneumaticController, false));

		jButton2.whileHeld(new VisionTracking(mAngleAdjust, mLimelight, mTurret, mJoystick));

		jButton3.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, -1.0));

		jButton4.whileHeld(new IntakeMotorsSetPercentOutput(mIntakeMotors, 1.0));

		jButton5.toggleWhenPressed(new IntakePistonsSetState(mIntakePistons, true));
		jButton6.toggleWhenPressed(new PanelSpinnerPistonSetState(mPanelSpinnerPiston, true));

		jButton7.whileHeld(new HopperSetPercentOutput(mHopper, 0.7));
		jButton8.whileHeld(new HopperSetPercentOutput(mHopper, -1.0));

		jButton9.whileHeld(new ClimberSetPercentOutput(mClimber, -1.0));
		jButton9.whileHeld(new PneumaticControllerCompressorSetState(mPneumaticController, false));

		jButton10.whileHeld(new RampSetState(mRamp, true));
		jButton11.whileHeld(new PanelSpinnerMotorSetPercentOutput(mPanelSpinnerMotor, 0.85));
		jButton12.whenPressed(new PanelSpinnerSpinToColor(mPanelSpinnerMotor));
	}

	public XboxController getController() {
		return mXbox;
	}

	public Joystick getJoystick() {
		return mJoystick;
	}

	public Command getAutonomousCommand() {
		StartingPosition startingPosition = positionChooser.getSelected();
		TargetTask targetTask = targetChooser.getSelected();

		return new AutonomousCommand(startingPosition, targetTask, mJoystick, mAngleAdjust, mDrivetrain, mFeederWheels, mHopper, mIntakeMotors, mIntakePistons, mLimelight, mPneumaticController, mRamp, mShooter, mTurret);
	}
}