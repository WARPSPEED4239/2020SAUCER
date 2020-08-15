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
    mPanelSpinnerMotor.resetCounter();
    mPanelSpinnerMotor.setStartingColorString(mPanelSpinnerMotor.getDetectedColorString());
  }

  @Override
  public void execute() {

    //write logic here
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
