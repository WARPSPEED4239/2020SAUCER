package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeMotors extends SubsystemBase {
 
  private WPI_VictorSPX motors = new WPI_VictorSPX(Constants.INTAKE_MOTORS);

  public IntakeMotors() {
    motors.configFactoryDefault();
    motors.setInverted(false);
    motors.setNeutralMode(NeutralMode.Brake);
    motors.configVoltageCompSaturation(12.0);
    motors.enableVoltageCompensation(true);
  }

  @Override
  public void periodic() {
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }
    
    motors.set(output);
  }
}
