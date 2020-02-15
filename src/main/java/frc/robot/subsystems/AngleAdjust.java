package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AngleAdjust extends SubsystemBase {
 
  private Servo leftServo = new Servo(Constants.SHOOTER_LEFT_SERVO);
  private Servo rightServo = new Servo(Constants.SHOOTER_RIGHT_SERVO);

  public AngleAdjust() {
  }

  @Override
  public void periodic() {
  }

  public void setPosition(double position) {
    if (position > 1.0) {
      position = 1.0;
    } else if (position < 0.0) {
      position = 0.0;
    }
    
    leftServo.set(position);
    rightServo.set(position);
  }
}
