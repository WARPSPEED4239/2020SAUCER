package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticController;
import frc.robot.subsystems.Shooter;

public class ShooterSetPercentOutput extends CommandBase {

  private double mOutput;
  private final Shooter mShooter;
  private final PneumaticController mPneumaticController;

  public ShooterSetPercentOutput(Shooter shooter, PneumaticController pneumaticController, double output) {
    mShooter = shooter;
    mPneumaticController = pneumaticController;
    mOutput = output;
    addRequirements(mShooter, mPneumaticController);
  }

  @Override
  public void initialize() {
    mPneumaticController.turnOffCompressor();
  }

  @Override
  public void execute() {
    mShooter.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
    mPneumaticController.turnOnCompressor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
