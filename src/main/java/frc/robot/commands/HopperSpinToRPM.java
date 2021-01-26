package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class HopperSpinToRPM extends CommandBase {

  private double mRPM;
  private final Hopper mHopper;

  public HopperSpinToRPM(Hopper hopper, double RPM) {
    mHopper = hopper;
    mRPM = RPM;
    addRequirements(mHopper);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mHopper.setVelocity(mRPM);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
