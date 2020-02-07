package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterAngleAdjust extends SubsystemBase {
 
  private Servo leftServo = new Servo(Constants.SHOOTER_LEFT_SERVO);
  private Servo rightServo = new Servo(Constants.SHOOTER_RIGHT_SERVO);

  public ShooterAngleAdjust() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
