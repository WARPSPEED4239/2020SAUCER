package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainArcadeDrive extends CommandBase {
  
  public DrivetrainArcadeDrive(Drivetrain mDrivetrain) { //TODO IDK WHAT I AM DOIG HERE
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() { //COPY PASTE FROM LAST YEAR'S CODE //TODO HOW DO I CALL RobotContainer.getXbox?
    double move = -RobotContainer.m_oi.xbox.getTriggerAxis(Hand.kRight) + Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);
    double rotate = -(.533333 * Math.pow(Robot.m_oi.xbox.getX(Hand.kLeft), 3) + .466666 *  Robot.m_oi.xbox.getX(Hand.kLeft));

    if (rotate > 0.85){
      rotate = 0.85;
    }
    else if (rotate < -0.85) {
      rotate = -0.85;
    }
    
    mDrivetrain.arcadeDrive(move, rotate); //TODO WHY CAN'T I REFERENCE Drivetrain.java?
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
