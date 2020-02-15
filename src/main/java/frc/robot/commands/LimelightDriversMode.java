package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class LimelightDriversMode extends CommandBase {

  private Limelight mLimelight;

  public LimelightDriversMode(Limelight limelight) {
    mLimelight = limelight;
    addRequirements(mLimelight);
  }

  @Override
  public void initialize() {
    mLimelight.setCamMode(1);
    mLimelight.setLEDMode(1);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
