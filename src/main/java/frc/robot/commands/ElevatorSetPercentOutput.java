package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorSetPercentOutput extends CommandBase {

  private double mOutput;
  private final Elevator mElevator;

  public ElevatorSetPercentOutput(Elevator elevator, double output) {
    mElevator = elevator;
    mOutput = output;
    addRequirements(mElevator);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mElevator.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
