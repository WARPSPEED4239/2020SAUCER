package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AngleAdjust;

public class AngleAdjustSetPosition extends CommandBase {
  
  private double mPosition;
  private final AngleAdjust mAngleAdjust;
  
  public AngleAdjustSetPosition(AngleAdjust angleAdjust, double position) {
    mAngleAdjust = angleAdjust;
    mPosition = position;
    addRequirements(mAngleAdjust);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mAngleAdjust.setPosition(mPosition);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
