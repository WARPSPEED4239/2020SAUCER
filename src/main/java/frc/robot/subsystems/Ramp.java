package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Ramp extends SubsystemBase {
 
  DoubleSolenoid piston = new DoubleSolenoid(Constants.RAMP_SOLENOID_FORWARD, Constants.RAMP_SOLENOID_REVERSE);

  public Ramp() {
  }

  @Override
  public void periodic() {
  }

  public void setPistonState (boolean up) {
    if (up) {
      piston.set(Value.kReverse);
    } else {
      piston.set(Value.kForward);
    }
  }
}
