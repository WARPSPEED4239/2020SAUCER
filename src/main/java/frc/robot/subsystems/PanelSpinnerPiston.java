package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PanelSpinnerPiston extends SubsystemBase {
 
  private DoubleSolenoid piston = new DoubleSolenoid(Constants.PANNEL_SPINNER_SOLENOID_FORWARD, Constants.PANNEL_SPINNER_SOLENOID_REVERSE);
  
  public PanelSpinnerPiston() {

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
