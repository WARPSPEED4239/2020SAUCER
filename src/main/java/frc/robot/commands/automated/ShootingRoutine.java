package frc.robot.commands.automated;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;

public class ShootingRoutine extends CommandBase {

  private double mFeederWheelsRPM;
  private double mHopperRPM;
  private double mShooterRPM;
  private final FeederWheels mFeederWheels;
  private final Hopper mHopper;
  private final Ramp mRamp;
  private final Shooter mShooter;

  public ShootingRoutine(FeederWheels feederWheels, Hopper hopper, Ramp ramp, Shooter shooter, double feederWheelsRPM, double hopperRPM, double shooterRPM) {
    mFeederWheels = feederWheels;
    mHopper = hopper;
    mRamp = ramp;
    mShooter = shooter;
    mFeederWheelsRPM = feederWheelsRPM;
    mHopperRPM = hopperRPM;
    mShooterRPM = shooterRPM;
    addRequirements(mFeederWheels, mHopper, mRamp, mShooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mFeederWheels.setVelocity(mFeederWheelsRPM);
    mShooter.setVelocity(mShooterRPM);

    if (mFeederWheels.getRPM() < mFeederWheelsRPM + 20.0 && mFeederWheels.getRPM() > mFeederWheelsRPM - 20.0 && mShooter.getRPM() < mShooterRPM + 20.0 && mShooter.getRPM() > mShooterRPM - 20.0) {
      mHopper.setVelocity(mHopperRPM);
      mRamp.setPistonState(true);
    } else {
      mHopper.setPercentOutput(0.0);
      mRamp.setPistonState(false);
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
