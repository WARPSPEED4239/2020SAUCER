package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakePistons extends SubsystemBase {
 
  private DoubleSolenoid pistons = new DoubleSolenoid(Constants.INTAKE_SOLENOID_FORWARD, Constants.INTAKE_SOLENOID_REVERSE);

  public IntakePistons() {
  }

  @Override
  public void periodic() {
  }

  public void setPistonsState (boolean up) {
    if (up) {
      pistons.set(Value.kReverse);
    } else {
      pistons.set(Value.kForward);
    }
  }
}
