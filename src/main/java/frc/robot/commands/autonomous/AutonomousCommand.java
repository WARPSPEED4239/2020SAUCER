package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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

public class AutonomousCommand extends SequentialCommandGroup {

  public AutonomousCommand(StartingPosition startingPosition, TargetTask targetTask, Joystick joystick, AngleAdjust angleAdjust, Drivetrain drivetrain, FeederWheels feederWheels, Hopper hopper, IntakeMotors intakeMotors, IntakePistons intakePistons, Limelight limelight, PneumaticController pneumaticController, Ramp ramp, Shooter shooter, Turret turret) {
    super();

    switch (startingPosition) {
      case Left:
        addCommands(new CommandLeft(startingPosition, targetTask, joystick, angleAdjust, drivetrain, feederWheels, hopper, intakeMotors, intakePistons, limelight, pneumaticController, ramp, shooter, turret));
        break;
      case Center:
        addCommands(new CommandCenter(startingPosition, targetTask, joystick, angleAdjust, drivetrain, feederWheels, hopper, intakeMotors, intakePistons, limelight, pneumaticController, ramp, shooter, turret));
        break;
      case Right:
        addCommands(new CommandRight(startingPosition, targetTask, joystick, angleAdjust, drivetrain, feederWheels, hopper, intakeMotors, intakePistons, limelight, pneumaticController, ramp, shooter, turret));
        break;
      default:
        addCommands(new WaitCommand(15.0));
        break;
      }
  }
}
