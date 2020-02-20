package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PneumaticController;

public class DrivetrainArcadeDrive extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final PneumaticController mPneumaticController;
  private final XboxController mController;
  
  public DrivetrainArcadeDrive(Drivetrain drivetrain, PneumaticController pneumaticController, XboxController controller) {
    mDrivetrain = drivetrain;
    mPneumaticController = pneumaticController;
    mController = controller;
    addRequirements(mDrivetrain, mPneumaticController);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double move = -mController.getTriggerAxis(Hand.kRight) + mController.getTriggerAxis(Hand.kLeft);
    double rotate = -(.533333 * Math.pow(mController.getX(Hand.kLeft), 3) + .466666 *  mController.getX(Hand.kLeft));

    if (rotate > 0.85){
      rotate = 0.85;
    }
    else if (rotate < -0.85) {
      rotate = -0.85;
    }
    
    mDrivetrain.arcadeDrive(move, rotate);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
