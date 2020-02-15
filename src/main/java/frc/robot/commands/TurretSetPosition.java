package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretSetPosition extends CommandBase {
  
  private double mAngleInDegrees;
  private final Turret mTurret;
  
  public TurretSetPosition(Turret turret, double angleInDegrees) {
    mTurret = turret;
    mAngleInDegrees = angleInDegrees;
    addRequirements(mTurret);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (mTurret.getLeftLimit() && mTurret.getMotorOutputVoltage() > Constants.EPSILON) {
      mTurret.setPercentOutput(0.0);
    } else if (mTurret.getRightLimit() && mTurret.getMotorOutputVoltage() < -Constants.EPSILON) {
      mTurret.setPercentOutput(0.0);
    } else {
      mTurret.setAngleInDegrees(mAngleInDegrees);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
