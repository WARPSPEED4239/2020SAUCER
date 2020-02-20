package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PanelSpinnerMotor;

public class PanelSpinnerSpinForRotations extends CommandBase {

  private double mRotations;
  private final PanelSpinnerMotor mPanelSpinnerMotor;

  public PanelSpinnerSpinForRotations(PanelSpinnerMotor panelSpinnerMotor, double rotations) {
    mPanelSpinnerMotor = panelSpinnerMotor;
    mRotations = rotations;
    addRequirements(mPanelSpinnerMotor);
  }

  @Override
  public void initialize() {
    mPanelSpinnerMotor.resetEncoder();
  }

  @Override
  public void execute() {
    mPanelSpinnerMotor.setSpinnerRotations(mRotations); //3 to 5 rotations needed
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
