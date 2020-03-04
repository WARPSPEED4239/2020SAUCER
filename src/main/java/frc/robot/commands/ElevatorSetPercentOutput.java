package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorSetPercentOutput extends CommandBase {

  private Joystick mJoystick;
  private final Elevator mElevator;

  public ElevatorSetPercentOutput(Elevator elevator, Joystick joystick) {
    mElevator = elevator;
    mJoystick = joystick;
    addRequirements(mElevator);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double mValue = mJoystick.getPOV(0);

    if (mValue == 0) {
      mElevator.setPercentOutput(0.5);
    } else if (mValue == 180) {
      mElevator.setPercentOutput(-0.5);
    } else {
        mElevator.setPercentOutput(0.0);
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
