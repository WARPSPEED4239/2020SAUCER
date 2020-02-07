package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederWheels extends SubsystemBase {
  private final double RAMP_RATE = 0.2;
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 30.0, 40.0, 1.0); //TODO If not working get rid of this?
  
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.FEEDER_MOTOR);
  
  public FeederWheels() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSupplyCurrentLimit(CURRENT_LIMIT);
    motor.configOpenloopRamp(RAMP_RATE);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    motor.setSensorPhase(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPercentOutput(double output) {
    if (output > 1) {
      output = 1;
    } else if (output < -1) {
      output = -1;
    }

    motor.set(output);
  }
}
