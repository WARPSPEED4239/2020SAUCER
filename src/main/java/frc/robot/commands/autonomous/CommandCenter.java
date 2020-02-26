package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;

public class CommandCenter extends SequentialCommandGroup {

  public CommandCenter(TargetTask targetTask) {
    // super(new FooCommand(), new BarCommand());
    super();
  }
}
