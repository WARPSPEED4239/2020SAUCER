package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberSetPercentOutput extends CommandBase {
  
  private double mOutput;
  private final Climber mClimber;

  public ClimberSetPercentOutput(Climber climber, double output) {
    mClimber = climber;
    mOutput = output;
    addRequirements(mClimber);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mClimber.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
