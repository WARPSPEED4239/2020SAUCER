package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederWheels;

public class FeederWheelsSpinToRPM extends CommandBase {
  
  private double mRPM;
  private final FeederWheels mFeederWheels;
  
  public FeederWheelsSpinToRPM(FeederWheels feederWheels, double RPM) {
    mFeederWheels = feederWheels;
    mRPM = RPM;
    addRequirements(mFeederWheels);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mFeederWheels.setVelocity(mRPM);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
