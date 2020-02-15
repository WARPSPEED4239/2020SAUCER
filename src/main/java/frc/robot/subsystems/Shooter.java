package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final double RAMP_RATE = 0.2;
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 40.0, 60.0, 1.0); //TODO If not working get rid of this?

  private WPI_TalonSRX motor1 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_BOTTOM);
  private WPI_TalonSRX motor2 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_TOP);

  public Shooter() {
    motor1.configFactoryDefault();
    motor2.configFactoryDefault();

    motor1.setInverted(true);
    motor2.setInverted(true);
  
    motor1.setNeutralMode(NeutralMode.Coast);
    motor2.setNeutralMode(NeutralMode.Coast);

    motor1.configClosedLoopPeakOutput(0, 1.0);
    motor2.configClosedLoopPeakOutput(0, 1.0);
  
    motor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    motor2.configSupplyCurrentLimit(CURRENT_LIMIT);

    motor1.configOpenloopRamp(RAMP_RATE);
    motor2.configOpenloopRamp(RAMP_RATE);
    
    motor2.follow(motor1);

    motor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TIMEOUT_MS);
    motor1.setSensorPhase(true);

    motor1.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    motor1.config_kP(0, 0.0, Constants.TIMEOUT_MS);
		motor1.config_kI(0, 0.0, Constants.TIMEOUT_MS);
		motor1.config_kD(0, 0.0, Constants.TIMEOUT_MS);
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
    
    motor1.set(output);
  }

  public void setVelocity(double RPM) {
    int VelocityInSRXUnits = (int) (RPM / 600.0 * Constants.COUNTS_PER_REVOLUTION_ENCODER);
    motor1.set(ControlMode.Velocity, VelocityInSRXUnits);
  }

  public double getRPM() {
    return motor1.getSelectedSensorVelocity();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("Shooter RPM", getRPM());
  }
}
