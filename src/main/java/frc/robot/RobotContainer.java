package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifting;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PanelSpinner;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Climber mClimber = new Climber();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final DrivetrainShifting mDrivetrainShifting = new DrivetrainShifting();
  private final Feeder mFeeder = new Feeder();
  private final Intake mIntake = new Intake();
  private final PanelSpinner mPanelSpinner = new PanelSpinner();
  private final Shooter mShooter = new Shooter();
  private final Turret mTurret = new Turret();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(mDrivetrain.getMaxVelocityMetersPerSecond(), mDrivetrain.getMaxAcellMetersPerSecondPerSecond());

    config.setKinematics(mDrivetrain.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);

    RamseteCommand command = new RamseteCommand(
      trajectory, 
      mDrivetrain::getPose, 
      new RamseteController(2.0, 0.7), 
      mDrivetrain.getFeedForward(), 
      mDrivetrain.getKinematics(), 
      mDrivetrain::getSpeeds, 
      mDrivetrain.getLeftPIDController(), 
      mDrivetrain.getRightPIDController(), 
      mDrivetrain::setOutput, 
      mDrivetrain
      );
    
    return command;
  }
}
