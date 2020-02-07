package frc.robot.subsystems;

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
    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    motor.setSensorPhase(false);
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
