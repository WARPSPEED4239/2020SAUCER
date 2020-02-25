package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.autonomous.SendableChoosers.StartingPosition;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;

public class AutonomousCommand extends SequentialCommandGroup {

  public AutonomousCommand(StartingPosition startingPosition, TargetTask targetTask) {
    super();

    switch (startingPosition) {
      case OpponetTrenchPerp:
        addCommands(new CommandOpponetTrenchPerp(targetTask));
        break;
      case LeftPerp:
        addCommands(new CommandLeftPerp(targetTask));
        break;
      case CenterPerp:
        addCommands(new CommandCenterPerp(targetTask));
        break;
      case CenterPara:
        addCommands(new CommandCenterPara(targetTask));
        break;
      case RightPerp:
        addCommands(new CommandRightPerp(targetTask));
        break;
      case RightPara:
        addCommands(new CommandRightPara(targetTask));
      default:
        addCommands(new WaitCommand(15.0));
        break;
      }
  }
}
