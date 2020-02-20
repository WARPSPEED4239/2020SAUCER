package frc.robot.commands.automated;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AngleAdjust;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class VisionTracking extends CommandBase {
  
  private final AngleAdjust mAngleAdjust;
  private final Limelight mLimelight;
  private final Turret mTurret;
  private Joystick mJoystick;

  private final double kPTurret = 0.02; //TODO TUNE
  private final double kHeightOfInnerGoalInches = 98.25;
  private final double kHeightOfLimelightInches = 0.0; //TODO TUNE
  private final double kAngleOfLimelightDegrees = 0.0; //TODO TUNE

  public VisionTracking(AngleAdjust angleAdjust, Limelight limelight, Turret turret, Joystick joystick) {
    mAngleAdjust = angleAdjust;
    mLimelight = limelight;
    mTurret = turret;
    mJoystick = joystick;
    addRequirements(mAngleAdjust, mLimelight, mTurret);
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

    if (tv == 1) {
      double turretMove;
      double angleAdjustPosition;
      double tx = mLimelight.getTx();
      double ty = mLimelight.getTy();
      double distanceToGoal = ((kHeightOfInnerGoalInches - kHeightOfLimelightInches) / (Math.tan(Units.degreesToRadians(kAngleOfLimelightDegrees + ty)))); 

      if (distanceToGoal < 348.0 && distanceToGoal >= 319.0) { //TODO tune these
        angleAdjustPosition = 0.45;
      } else if (distanceToGoal < 290.0 && distanceToGoal >= 261.0) {
        angleAdjustPosition = 0.5;
      } else if (distanceToGoal < 261.0 && distanceToGoal >= 232.0) {
        angleAdjustPosition = 0.55;
      } else if (distanceToGoal < 232.0 && distanceToGoal >= 203.0) {
        angleAdjustPosition = 0.6;
      } else if (distanceToGoal < 203.0 && distanceToGoal >= 174.0) {
        angleAdjustPosition = 0.65;
      } else if (distanceToGoal < 174.0 && distanceToGoal >= 145.0) {
        angleAdjustPosition = 0.7;
      } else if (distanceToGoal < 145.0 && distanceToGoal >= 116.0) {
        angleAdjustPosition = 0.75;
      } else if (distanceToGoal < 116.0 && distanceToGoal >= 87.0) {
        angleAdjustPosition = 0.8;
      } else if (distanceToGoal < 87.0 && distanceToGoal >= 58.0) {
        angleAdjustPosition = 0.85;
      } else if (distanceToGoal < 58.0 && distanceToGoal >= 29.0) {
        angleAdjustPosition = 0.9;
      } else if (distanceToGoal < 58.0 && distanceToGoal >= 29.0) {
        angleAdjustPosition = 0.95;
      } else {
        angleAdjustPosition = 1.0;
      }

      if (mTurret.getLeftLimit() && mTurret.getMotorOutputVoltage() > Constants.EPSILON) { //TODO Check direction
        turretMove = 0.0;
      } else if (mTurret.getRightLimit() && mTurret.getMotorOutputVoltage() < -Constants.EPSILON) {
        turretMove = 0.0;
      } else if (Math.abs(tx) < 0.5) {
          turretMove = 0.0;
      } else {
        turretMove = kPTurret * tx;
      }

      mTurret.setPercentOutput(turretMove);
      mAngleAdjust.setPosition(angleAdjustPosition);
    } else {
      double mOutput = mJoystick.getRawAxis(0);

      if (mTurret.getLeftLimit() && mTurret.getMotorOutputVoltage() > Constants.EPSILON) { //TODO Check direction
        mOutput = 0.0;
      } else if (mTurret.getRightLimit() && mTurret.getMotorOutputVoltage() < -Constants.EPSILON) {
        mOutput = 0.0;
      } else if (mOutput < 0.15 && mOutput > -0.15) {
        mOutput = 0.0;
      }

      mTurret.setPercentOutput(mOutput);
    }
  }

  @Override
  public void end(boolean interrupted) {
    mLimelight.setCamMode(1);
    mLimelight.setLEDMode(1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
