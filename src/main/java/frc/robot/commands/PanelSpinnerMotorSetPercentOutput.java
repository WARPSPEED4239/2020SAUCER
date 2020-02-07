package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PanelSpinnerMotor;

public class PanelSpinnerMotorSetPercentOutput extends CommandBase {

  private double mOutput;
  private final PanelSpinnerMotor mPanelSpinnerMotor;

  public PanelSpinnerMotorSetPercentOutput(PanelSpinnerMotor panelSpinnerMotor, double output) {
    mPanelSpinnerMotor = panelSpinnerMotor;
    mOutput = output;
    addRequirements(mPanelSpinnerMotor);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mPanelSpinnerMotor.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
