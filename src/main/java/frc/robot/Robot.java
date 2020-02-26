package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autonomous.AutonomousCommand;
import frc.robot.commands.autonomous.Trajectories;
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
    mRobotContainer.configureButtonBindings();

    UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(320, 240);
    cam0.setFPS(10);

    Trajectories.initialize();

    positionChooser.addOption("Left", StartingPosition.LeftPerp);
    positionChooser.addOption("Center", StartingPosition.CenterPerp);
    positionChooser.addOption("Right", StartingPosition.RightPerp);
    SmartDashboard.putData(positionChooser);

    targetChooser.setDefaultOption("Shoot 3", TargetTask.Shoot3);
    targetChooser.addOption("Steal 2 Shoot 5", TargetTask.Steal2Shoot5);
    targetChooser.addOption("Shoot 3 Grab 5 Shoot 5", TargetTask.Shoot3Grab5Shoot5);
    targetChooser.addOption("Shoot 3 Grab 3 Shoot 3", TargetTask.Shoot3Grab3Shoot3);
    targetChooser.addOption("Drive Forward", TargetTask.DriveForward);
    targetChooser.addOption("Do Nothing", TargetTask.DoNothing);
    SmartDashboard.putData(targetChooser);
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
    StartingPosition startingPosition = positionChooser.getSelected();
		TargetTask targetTask = targetChooser.getSelected();
    
    mAutonomousCommand = new AutonomousCommand(startingPosition, targetTask);
    
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
