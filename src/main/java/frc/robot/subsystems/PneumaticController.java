package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticController extends SubsystemBase {

  private final Compressor mCompressor = new Compressor(Constants.COMPRESSOR);
  private final AnalogInput mPressureSensor = new AnalogInput(Constants.PRESSURE_SENSOR);

  private final double DEFAULT_VOLTS = 4.52;
  private final double SLOPE = 250.0;
  private final double Y_INTERCEPT = -25.0;

  public PneumaticController() {
  }

  @Override
  public void periodic() {
  }

  public void turnOffCompressor() {
    mCompressor.stop();
  }

  public void turnOnCompressor() {
    mCompressor.start();
  }

  public boolean getCompressorStatus() {
    return mCompressor.enabled();
  }

  public double getTankPressure() {
    return SLOPE * (mPressureSensor.getVoltage() / DEFAULT_VOLTS) + Y_INTERCEPT;
  }

  public double getPressureSensorVolts() {
    return mPressureSensor.getVoltage();
  }
}
