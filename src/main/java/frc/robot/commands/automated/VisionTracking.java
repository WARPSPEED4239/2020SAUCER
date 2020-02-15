package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.FeederWheels;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Ramp;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class VisionTracking extends CommandBase {
  
  private final AngleAdjust mAngleAdjust;
  private final FeederWheels mFeederWheels;
  private final Hopper mHopper;
  private final Limelight mLimelight;
  private final Shooter mShooter;
  private final Ramp mRamp;
  private final Turret mTurret;

  private final double kPTurret = 0.02; //TODO TUNE
  private final double kPAngleAdjust = 0.01;
  private final double kHeightOfInnerGoalInches = 98.25;
  private final double kHeightOfLimelightInches = 0.0; //TODO TUNE
  private final double kAngleOfLimelightDegrees = 0.0; //TODO TUNE

  public VisionTracking(AngleAdjust angleAdjust, FeederWheels feederWheels, Hopper hopper, Limelight limelight, Ramp ramp, Shooter shooter, Turret turret) {
    mAngleAdjust = angleAdjust;
    mFeederWheels = feederWheels;
    mHopper = hopper;
    mLimelight = limelight;
    mRamp = ramp;
    mShooter = shooter;
    mTurret = turret;
    addRequirements(mAngleAdjust, mFeederWheels, mHopper, mLimelight, mRamp, mShooter, mTurret);
  }

  @Override
  public void initialize() {
    mLimelight.setCamMode(0);
    mLimelight.setPipeline(1);
    mLimelight.setLEDMode(3);
  }

  @Override
  public void execute() {
    double tv = mLimelight.getTv();

    double turretMove;
    double angleAdjustMove;

    if (tv == 1) {
      double tx = mLimelight.getTx();
      double ty = mLimelight.getTy();
      double distanceToGoal = ((kHeightOfInnerGoalInches - kHeightOfLimelightInches) / (Math.tan(Units.degreesToRadians(kAngleOfLimelightDegrees + ty)))); 

      if (Math.abs(tx) < 1.0) {
        turretMove = 0;
      } else {
        turretMove = kPTurret * tx;
      }

      

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
