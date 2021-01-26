package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class HopperSetPercentOutput extends CommandBase {

  private double mOutput;
  private final Hopper mHopper;

  public HopperSetPercentOutput(Hopper hopper, double output) {
    mHopper = hopper;
    mOutput = output;
    addRequirements(mHopper);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mHopper.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
