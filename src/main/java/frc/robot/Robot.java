package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.PanelSpinnerMotor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class Robot extends TimedRobot {
  private String gameData;
  private Command mAutonomousCommand;
  private PanelSpinnerMotor mPanelSpinnerMotor;
  private Turret mTurret;
  private Shooter mShooter;

  private RobotContainer mRobotContainer;

  @Override
  public void robotInit() {
    mRobotContainer = new RobotContainer();
    mPanelSpinnerMotor = new PanelSpinnerMotor();
    mTurret = new Turret();
    mShooter = new Shooter();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    mPanelSpinnerMotor.updateColorSensorData();

    if (mTurret.getLeftLimit()) {
      mTurret.setEncoderAngleInDegrees(-90.0);
    } else if (mTurret.getRightLimit()) {
      mTurret.setEncoderAngleInDegrees(330.0); //TODO Put in actual number
    }

    mShooter.updateSmartDashboard();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    mAutonomousCommand = mRobotContainer.getAutonomousCommand();

    if (mAutonomousCommand != null) {
      mAutonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (mAutonomousCommand != null) {
      mAutonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B':
          mPanelSpinnerMotor.setGameDataColor("Blue");
          break;
        case 'G':
          mPanelSpinnerMotor.setGameDataColor("Green");
          break;
        case 'R':
          mPanelSpinnerMotor.setGameDataColor("Red");
          break;
        case 'Y':
          mPanelSpinnerMotor.setGameDataColor("Yellow");
          break;
        default:
          mPanelSpinnerMotor.setGameDataColor("Error");
          break;
      }
    }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
