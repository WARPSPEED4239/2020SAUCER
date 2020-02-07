package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterSetPercentOutput extends CommandBase {

  private double mOutput;
  private final Shooter mShooter;

  public ShooterSetPercentOutput(Shooter shooter, double output) {
    mShooter = shooter;
    mOutput = output;
    addRequirements(mShooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mShooter.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
