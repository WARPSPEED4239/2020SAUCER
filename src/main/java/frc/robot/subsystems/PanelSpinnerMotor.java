package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PanelSpinnerMotor extends SubsystemBase {
 
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.PANNEL_SPINNER_MOTOR);

  public PanelSpinnerMotor() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    motor.setSensorPhase(false);
    motor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
  }
}
