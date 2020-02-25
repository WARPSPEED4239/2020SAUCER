package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticController;

public class PneumaticControllerCompressorSetState extends CommandBase {

  private boolean mOn;
  private final PneumaticController mPneumaticController;

  public PneumaticControllerCompressorSetState(PneumaticController pneumaticController, boolean on) {
    mPneumaticController = pneumaticController;
    mOn = on;
    addRequirements(mPneumaticController);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mPneumaticController.setCompressorState(mOn);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
