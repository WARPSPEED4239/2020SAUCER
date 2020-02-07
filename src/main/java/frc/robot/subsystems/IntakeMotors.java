package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeMotors extends SubsystemBase {
 
  private WPI_TalonSRX motors = new WPI_TalonSRX(Constants.INTAKE_MOTORS);

  public IntakeMotors() {
    motors.configFactoryDefault();
    motors.setInverted(false);
    motors.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
  }

  public void setPercentOutput(double motorOutput) {
    if (motorOutput > 1) {
      motorOutput = 1;
    } else if (motorOutput < -1) {
      motorOutput = -1;
    }
    
    motors.set(motorOutput);
  }
}
