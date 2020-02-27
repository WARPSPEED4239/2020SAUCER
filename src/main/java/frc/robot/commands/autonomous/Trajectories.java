package frc.robot.commands.autonomous;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

public class Trajectories {
    public static Trajectory driveForward1M;
    public static Trajectory rightTrenchRun3;
    public static Trajectory rightTrenchRun5;
    public static Trajectory rightTrenchRunInRange;
    public static Trajectory leftTrenchSteal;
    public static Trajectory leftTrenchMoveToShoot;
    
    public static void initialize(TrajectoryConfig config) {
      driveForward1M = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
      rightTrenchRun3 = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
      rightTrenchRun5 = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
      rightTrenchRunInRange = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
      leftTrenchSteal = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
      leftTrenchMoveToShoot = TrajectoryGenerator.generateTrajectory(new Pose2d(), Arrays.asList(), new Pose2d(1.0, 0.0, Rotation2d.fromDegrees(0)), config.setReversed(false));
    }
}