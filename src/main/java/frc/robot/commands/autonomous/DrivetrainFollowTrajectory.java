package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainFollowTrajectory extends RamseteCommand {
  public DrivetrainFollowTrajectory(Drivetrain drivetrain, Trajectory trajectory) {
    super(trajectory, drivetrain::getPose, new RamseteController(), drivetrain.getKinematics(), drivetrain::setVelocity, drivetrain);
  }
}