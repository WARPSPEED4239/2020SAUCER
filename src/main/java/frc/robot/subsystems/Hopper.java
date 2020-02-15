package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.HOPPER_MOTOR);
  
  public Hopper() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TIMEOUT_MS);
    motor.setSensorPhase(false);

    motor.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    motor.config_kP(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kI(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kD(0, 0.0, Constants.TIMEOUT_MS);
  }

  @Override
  public void periodic() {
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }

    motor.set(output);
  }

  public void setVelocity(double RPM) { //TODO Velocity? Position? What is needed if any.
    int VelocityInSRXUnits = (int) (RPM / 600 * Constants.COUNTS_PER_REVOLUTION_ENCODER);
    motor.set(ControlMode.Velocity, VelocityInSRXUnits);
  }
}
