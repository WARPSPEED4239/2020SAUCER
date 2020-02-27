package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.autonomous.SendableChoosers.StartingPosition;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;
import frc.robot.subsystems.Drivetrain;

public class AutonomousCommand extends SequentialCommandGroup {

  public AutonomousCommand(StartingPosition startingPosition, TargetTask targetTask, Drivetrain drivetrain) {
    super();

    switch (startingPosition) {
      case OpponetTrenchPerp:
        addCommands(new CommandLeft(targetTask, drivetrain));
        break;
      case LeftPerp:
        addCommands(new CommandRight(targetTask, drivetrain));
        break;
      case CenterPerp:
        addCommands(new CommandCenter(targetTask, drivetrain));
        break;
      default:
        addCommands(new WaitCommand(15.0));
        break;
      }
  }
}
