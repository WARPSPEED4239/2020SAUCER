package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
 
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.TURRET_MOTOR);
  private DigitalInput leftLimit = new DigitalInput(Constants.TURRET_LEFT_LIMIT);
  private DigitalInput rightLimit = new DigitalInput(Constants.TURRET_RIGHT_LIMIT);

  public Turret() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    motor.setSensorPhase(false);
  }

  @Override
  public void periodic() {
  }

  public boolean getLeftLimit() {
    return !leftLimit.get();
  }

  public boolean getRightLimit() {
    return !rightLimit.get();
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }
    
    motor.set(output);
  }

  public void setAngle(double degrees) {
    double angleInSRXUnits = degrees / 360 * 14.5 / 1.3 * Constants.COUNTS_PER_REVOLUTION_ENCODER;
    motor.set(ControlMode.MotionMagic, angleInSRXUnits);
  }
}
