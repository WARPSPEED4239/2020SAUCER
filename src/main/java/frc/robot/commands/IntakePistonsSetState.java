package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakePistons;

public class IntakePistonsSetState extends CommandBase {

  private boolean mUp;
  private final IntakePistons mIntakePistons;

  public IntakePistonsSetState(IntakePistons intakePistons, boolean up) {
    mIntakePistons = intakePistons;
    mUp = up;
    addRequirements(mIntakePistons);
  }

  @Override
  public void initialize() {
    mIntakePistons.setPistonsState(mUp);
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
