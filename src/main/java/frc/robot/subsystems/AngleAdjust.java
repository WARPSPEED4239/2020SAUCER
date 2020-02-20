package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AngleAdjust extends SubsystemBase {
 
  private Servo servo = new Servo(Constants.ANGLE_ADJUST_SERVO);

  public AngleAdjust() {
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Angle Adjust Position", getPosition());
  }

  public void setPosition(double position) {
    if (position > 1.0) {
      position = 1.0;
    } else if (position < 0.0) {
      position = 0.0;
    }
    
    servo.set(position);
  }

  public double getPosition() {
    return servo.getPosition();
  }
}
