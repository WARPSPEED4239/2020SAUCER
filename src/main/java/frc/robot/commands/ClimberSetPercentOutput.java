package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.PneumaticController;

public class ClimberSetPercentOutput extends CommandBase {
  
  private double mOutput;
  private final Climber mClimber;
  private final PneumaticController mPneumaticController;

  public ClimberSetPercentOutput(Climber climber, PneumaticController pneumaticController, double output) {
    mClimber = climber;
    mPneumaticController = pneumaticController;
    mOutput = output;
    addRequirements(mClimber, mPneumaticController);
  }

  @Override
  public void initialize() {
    mPneumaticController.turnOffCompressor();
  }

  @Override
  public void execute() {
    mClimber.setPercentOutput(mOutput);
  }

  @Override
  public void end(boolean interrupted) {
    mPneumaticController.turnOnCompressor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
