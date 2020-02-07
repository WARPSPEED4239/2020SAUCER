package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederWheels;

public class FeederWheelsSetPercentOutput extends CommandBase {

  private double mOutput;
  private final FeederWheels mFeederWheels;

  public FeederWheelsSetPercentOutput(FeederWheels feederWheels, double output) {
    mFeederWheels = feederWheels;
    mOutput = output;
    addRequirements(mFeederWheels);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mFeederWheels.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
