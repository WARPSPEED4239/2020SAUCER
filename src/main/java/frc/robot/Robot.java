package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autonomous.SendableChoosers.StartingPosition;
import frc.robot.commands.autonomous.SendableChoosers.TargetTask;

public class Robot extends TimedRobot {
  private Command mAutonomousCommand;

  private SendableChooser<StartingPosition> positionChooser = new SendableChooser<>();
  private SendableChooser<TargetTask> targetChooser = new SendableChooser<>();
  
  private RobotContainer mRobotContainer;

  @Override
  public void robotInit() { 
    mRobotContainer = new RobotContainer();

    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
    cam0.setFPS(10);

    positionChooser.setDefaultOption("Opponet Trench Perp", StartingPosition.OpponetTrenchPerp);
    positionChooser.addOption("Opponet Trench Para", StartingPosition.OpponetTrenchPara);
    positionChooser.addOption("Left Perp", StartingPosition.LeftPerp);
    positionChooser.addOption("Left Para", StartingPosition.LeftPara);
    positionChooser.addOption("Center Perp", StartingPosition.CenterPerp);
    positionChooser.addOption("Center Para", StartingPosition.CenterPara);
    positionChooser.addOption("Right Perp", StartingPosition.RightPerp);
    positionChooser.addOption("Right Para", StartingPosition.RightPara);
    positionChooser.addOption("Trench Perp", StartingPosition.TrenchPerp);
    positionChooser.addOption("Trench Para", StartingPosition.TrenchPara);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
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
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
