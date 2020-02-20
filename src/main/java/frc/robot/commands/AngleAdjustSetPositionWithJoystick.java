package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AngleAdjust;

public class AngleAdjustSetPositionWithJoystick extends CommandBase {

  private final AngleAdjust mAngleAdjust;
  private Joystick mJoystick;

  public AngleAdjustSetPositionWithJoystick(AngleAdjust angleAdjust, Joystick joystick) {
    mAngleAdjust = angleAdjust;
    mJoystick = joystick;
    addRequirements(mAngleAdjust);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double position = (-mJoystick.getThrottle() + 1.0) / 2.0; //1 to -1 --> 0 to 1

    mAngleAdjust.setPosition(position);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
