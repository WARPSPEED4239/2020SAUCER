package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final double RAMP_RATE = 0.2;
  private final SupplyCurrentLimitConfiguration CURRENT_LIMIT = new SupplyCurrentLimitConfiguration (true, 40.0, 60.0, 1.0); //TODO If not working get rid of this?

  private WPI_TalonSRX motor1 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_TOP);
  private WPI_TalonSRX motor2 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_BOTTOM);

  public Shooter() {
    motor1.configFactoryDefault();
    motor2.configFactoryDefault();

    motor1.setInverted(true);
    motor2.setInverted(true);
  
    motor1.setNeutralMode(NeutralMode.Coast);
    motor2.setNeutralMode(NeutralMode.Coast);

    motor1.configSupplyCurrentLimit(CURRENT_LIMIT);
    motor2.configSupplyCurrentLimit(CURRENT_LIMIT);

    motor1.configOpenloopRamp(RAMP_RATE);
    motor2.configOpenloopRamp(RAMP_RATE);
    
    motor2.follow(motor1);

    motor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    motor1.setSensorPhase(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
