package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticController;
import frc.robot.subsystems.Shooter;

public class ShooterSpinToRPM extends CommandBase {
  
  private double mRPM;
  private final Shooter mShooter;
  private final PneumaticController mPneumaticController;
  
  public ShooterSpinToRPM(Shooter shooter, PneumaticController pneumaticController, double RPM) {
    mShooter = shooter;
    mPneumaticController = pneumaticController;
    mRPM = RPM;
    addRequirements(mShooter, mPneumaticController);
  }

  @Override
  public void initialize() {
    mPneumaticController.turnOffCompressor();
  }

  @Override
  public void execute() {
    mShooter.setVelocity(mRPM);
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
