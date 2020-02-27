package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;
import frc.robot.subsystems.Drivetrain;

public class CommandRight extends SequentialCommandGroup {

  public CommandRight(TargetTask targetTask, Drivetrain drivetrain) {
    switch (targetTask) {
      case Shoot3:
        addCommands(new CommandLeft(targetTask));
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
