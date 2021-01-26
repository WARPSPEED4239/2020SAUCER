package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PanelSpinnerMotor;

public class PanelSpinnerSpinToColor extends CommandBase {
  
  private String mTargetColor;
  private final PanelSpinnerMotor mPanelSpinnerMotor;
  
  public PanelSpinnerSpinToColor(PanelSpinnerMotor panelSpinnerMotor) {
    mPanelSpinnerMotor = panelSpinnerMotor;
    addRequirements(mPanelSpinnerMotor);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mTargetColor = mPanelSpinnerMotor.getTargetColor();

    if (mTargetColor == "Blue" && mPanelSpinnerMotor.getDetectedColorString() != "Red") {
      mPanelSpinnerMotor.setPercentOutput(0.9);
    } else if (mTargetColor == "Red" && mPanelSpinnerMotor.getDetectedColorString() != "Blue") {
      mPanelSpinnerMotor.setPercentOutput(0.9);
    } else if (mTargetColor == "Green" && mPanelSpinnerMotor.getDetectedColorString() != "Yellow") {
      mPanelSpinnerMotor.setPercentOutput(0.9);
    } else if (mTargetColor == "Yellow" && mPanelSpinnerMotor.getDetectedColorString() != "Green") {
      mPanelSpinnerMotor.setPercentOutput(0.9);
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
