package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainShifting;

public class DrivetrainShiftingSetState extends CommandBase {

  private boolean mHighGear;
  private final DrivetrainShifting mDrivetrainShifting;

  public DrivetrainShiftingSetState(DrivetrainShifting drivetrainShifting, boolean highGear) {
    mDrivetrainShifting = drivetrainShifting;
    mHighGear = highGear;
    addRequirements(mDrivetrainShifting);
  }

  @Override
  public void initialize() {
    mDrivetrainShifting.setPistonsState(mHighGear);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
