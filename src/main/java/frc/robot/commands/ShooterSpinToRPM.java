package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterSpinToRPM extends CommandBase {
  
  private double mRPM;
  private final Shooter mShooter;
  
  public ShooterSpinToRPM(Shooter shooter, double RPM) {
    mShooter = shooter;
    mRPM = RPM;
    addRequirements(mShooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mShooter.setVelocity(mRPM);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
