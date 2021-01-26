package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 30.0, 40.0, 1.0);
  private final double RAMP_RATE = 0.3;

  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.CLIMBER_MOTOR);
  
  public Climber() {
    motor.configFactoryDefault();
    motor.setInverted(true);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSupplyCurrentLimit(CURRENT_LIMIT);
    motor.configOpenloopRamp(RAMP_RATE);
    motor.configVoltageCompSaturation(12.0);
    motor.enableVoltageCompensation(true);
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
}
