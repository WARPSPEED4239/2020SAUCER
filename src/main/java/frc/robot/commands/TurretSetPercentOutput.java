package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class TurretSetPercentOutput extends CommandBase {

  private double mOutput;
  private final Turret mTurret;

  public TurretSetPercentOutput(Turret turret, double output) {
    mTurret = turret;
    mOutput = output;
    addRequirements(mTurret);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mTurret.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
