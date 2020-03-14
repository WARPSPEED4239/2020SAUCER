package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederWheels extends SubsystemBase {
  private final double RAMP_RATE = 0.3;
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 30.0, 40.0, 1.0);
  
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.FEEDER_WHEELS_MOTOR);
  
  public FeederWheels() {
    motor.configFactoryDefault();
    motor.setInverted(true);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSupplyCurrentLimit(CURRENT_LIMIT);
    motor.configOpenloopRamp(RAMP_RATE);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TIMEOUT_MS);
    motor.setSensorPhase(true);

    motor.configVoltageCompSaturation(12.0);
    motor.enableVoltageCompensation(true);

    motor.config_kF(0, 0.01, Constants.TIMEOUT_MS);
    motor.config_kP(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kI(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kD(0, 0.0, Constants.TIMEOUT_MS);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Feeder Wheels RPM", getRPM());
    SmartDashboard.putNumber("Feeder Wheels Target RPM", 3000.0); //TODO find out what RPM we want
    SmartDashboard.putNumber("Feeder Wheels Encoder Value", motor.getSelectedSensorPosition());
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
