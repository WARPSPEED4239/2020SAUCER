package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class IntakeMotorsSetPercentOutput extends CommandBase {
  
  private double mOutput;
  private final IntakeMotors mIntakeMotors;

  public IntakeMotorsSetPercentOutput(IntakeMotors intakeMotors, double output) {
    mIntakeMotors = intakeMotors;
    mOutput = output;
    addRequirements(mIntakeMotors);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mIntakeMotors.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
