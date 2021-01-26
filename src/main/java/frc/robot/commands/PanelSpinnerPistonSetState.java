package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PanelSpinnerPiston;

public class PanelSpinnerPistonSetState extends CommandBase {

  private boolean mUp;
  private final PanelSpinnerPiston mPanelSpinnerPiston;

  public PanelSpinnerPistonSetState(PanelSpinnerPiston panelSpinnerPiston, boolean up) {
    mPanelSpinnerPiston = panelSpinnerPiston;
    mUp = up;
    addRequirements(mPanelSpinnerPiston);
  }

  @Override
  public void initialize() {
    mPanelSpinnerPiston.setPistonState(mUp);
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
