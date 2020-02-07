package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ramp;

public class RampSetState extends CommandBase {

  private boolean mUp;
  private final Ramp mRamp;

  public RampSetState(Ramp ramp, boolean up) {
    mRamp = ramp;
    mUp = up;
    addRequirements(mRamp);
  }

  @Override
  public void initialize() {
    mRamp.setPistonState(mUp);
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
