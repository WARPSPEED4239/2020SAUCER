package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.automated.ShootingRoutine;
import frc.robot.commands.automated.VisionTracking;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;

public class CommandLeft extends SequentialCommandGroup {

  public CommandLeft(TargetTask targetTask, Drivetrain drivetrain, FeederWheels feederWheels, Hopper hopper, Ramp ramp, Shooter shooter) {
    switch (targetTask) {
      case Shoot3:
        addCommands(new ParallelRaceGroup(
                      new VisionTracking(angleAdjust, limelight, turret, joystick),
                      new ShootingRoutine(feederWheels, hopper, ramp, shooter, feederWheelsRPM, hopperRPM, shooterRPM)),
                    new DrivetrainFollowTrajectory(drivetrain, Trajectories.driveForward1M));
        break;
      case Steal2Shoot5:
        addCommands(new CommandRight(targetTask));
        break;
      case Shoot3Grab5Shoot5:
        addCommands(new CommandCenter(targetTask));
        break;
      case Shoot3Grab3Shoot3:
        addCommands(new CommandCenter(targetTask));
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
