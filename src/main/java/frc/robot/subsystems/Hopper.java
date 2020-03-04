package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.HOPPER_MOTOR);
  
  public Hopper() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TIMEOUT_MS);
    motor.setSensorPhase(false); //TODO Check this

    motor.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    motor.config_kP(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kI(0, 0.0, Constants.TIMEOUT_MS);
    motor.config_kD(0, 0.0, Constants.TIMEOUT_MS);
    
    motor.configVoltageCompSaturation(12.0);
    motor.enableVoltageCompensation(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Hopper RPM", getRPM());
    SmartDashboard.putNumber("Hopper Target RPM", 40.0);
    SmartDashboard.putNumber("Hopper Encoder Value", motor.getSelectedSensorPosition());
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }

    motor.set(output);
  }

  public void setVelocity(double RPM) {
    int VelocityInSRXUnits = (int) (RPM / 600.0 * Constants.COUNTS_PER_REVOLUTION_ENCODER);
    motor.set(ControlMode.Velocity, VelocityInSRXUnits);
  }

  public double getRPM() {
    double RPM = motor.getSelectedSensorVelocity() * 600.0 / Constants.COUNTS_PER_REVOLUTION_ENCODER;
    return RPM;
  }
}
