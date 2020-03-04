package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.automated.ShootingRoutine;
import frc.robot.commands.automated.VisionTracking;
import frc.robot.commands.autonomous.SendableChoosers.StartingPosition;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.IntakePistons;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.PneumaticController;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class CommandCenter extends SequentialCommandGroup {

  public CommandCenter(StartingPosition startingPosition, TargetTask targetTask, Joystick joystick, AngleAdjust angleAdjust, Drivetrain drivetrain, FeederWheels feederWheels, Hopper hopper, IntakeMotors intakeMotors, IntakePistons intakePistons, Limelight limelight, PneumaticController pneumaticController, Ramp ramp, Shooter shooter, Turret turret) {
    switch (targetTask) {
      case Shoot3:
      addCommands(new ParallelRaceGroup(
        new VisionTracking(angleAdjust, limelight, turret, joystick),
        new ShootingRoutine(feederWheels, hopper, ramp, shooter, Constants.FEEDER_WHEELS_RPM, Constants.HOPPER_RPM, Constants.SHOOTER_RPM).withTimeout(5.0)),
      new DrivetrainFollowTrajectory(drivetrain, Trajectories.driveForward1M));
        break;
      case Steal2Shoot5:
        addCommands(new WaitCommand(1.0));
        break;
      case Shoot3Grab5Shoot5:
        addCommands(new WaitCommand(1.0));
        break;
      case Shoot3Grab3Shoot3:
        addCommands(new WaitCommand(1.0));
        break;
      case DriveForward:
        addCommands(new DrivetrainFollowTrajectory(drivetrain, Trajectories.driveForward1M));
        break;
        case DoNothing:
        addCommands(new WaitCommand(15.0));
        break;
      default:
        addCommands(new DrivetrainFollowTrajectory(drivetrain, Trajectories.driveForward1M));
        break;
    }
  }
}