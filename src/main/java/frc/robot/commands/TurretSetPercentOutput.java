package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class TurretSetPercentOutput extends CommandBase {

  private Joystick mJoystick;
  private final Turret mTurret;

  public TurretSetPercentOutput(Turret turret, Joystick joystick) {
    mTurret = turret;
    mJoystick = joystick;
    addRequirements(mTurret);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double mOutput = mJoystick.getRawAxis(0);

    if (mTurret.getLeftLimit() && mTurret.getMotorOutputVoltage() > Constants.EPSILON) { //TODO Check direction
      mTurret.setPercentOutput(0.0);
    } else if (mTurret.getRightLimit() && mTurret.getMotorOutputVoltage() < -Constants.EPSILON) {
      mTurret.setPercentOutput(0.0);
    } else if (mOutput < 0.15 && mOutput > -0.15) {
      mTurret.setPercentOutput(0.0);
    } else {
      mTurret.setPercentOutput(mOutput);
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
