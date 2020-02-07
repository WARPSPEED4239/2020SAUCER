package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private final int CURRENT_LIMIT = 30;

  private CANSparkMax motor = new CANSparkMax(Constants.CLIMBER_MOTOR, MotorType.kBrushless);
  
  public Climber() {
    motor.restoreFactoryDefaults();
    motor.setInverted(false);
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(CURRENT_LIMIT);
    motor.burnFlash();
  }

  @Override
  public void periodic() {
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
