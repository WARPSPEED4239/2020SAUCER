package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainArcadeDrive extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final XboxController mController;
  
  public DrivetrainArcadeDrive(Drivetrain drivetrain, XboxController controller) {
    mDrivetrain = drivetrain;
    mController = controller;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double move = mController.getTriggerAxis(Hand.kRight) - mController.getTriggerAxis(Hand.kLeft);
    double rotate = (.533333 * Math.pow(mController.getX(Hand.kLeft), 3) + .466666 *  mController.getX(Hand.kLeft));

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
