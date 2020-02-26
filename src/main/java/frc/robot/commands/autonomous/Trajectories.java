package frc.robot.commands.autonomous;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.subsystems.Drivetrain;

public class Trajectories {

    private static Drivetrain mDrivetrain;

    public static void initialize() {
        var voltageConstraint = new DifferentialDriveVoltageConstraint(mDrivetrain.getFeedForward(), mDrivetrain.getKinematics(), 9.0);
        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(6.0), Units.feetToMeters(3.0)).setKinematics(mDrivetrain.getKinematics()).addConstraint(voltageConstraint);

        config.setKinematics(mDrivetrain.getKinematics());

        Trajectory driveForward1M = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
        Trajectory rightTrenchRun3 = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
        Trajectory rightTrenchRun5 = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
        Trajectory rightTrenchRunInRange = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
        Trajectory leftTrenchSteal = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
        Trajectory leftTrenchMoveToShoot = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d())), config);
    }

}