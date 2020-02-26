package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Drive1M extends SequentialCommandGroup {
  
  public Drive1M(Drivetrain drivetrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new DrivetrainFollowTrajectory(drivetrain, trajectory));
  }
}
