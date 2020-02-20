package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PanelSpinnerMotor extends SubsystemBase {

  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.PANNEL_SPINNER_MOTOR);
  private I2C.Port i2cPort = I2C.Port.kOnboard;
  private ColorSensorV3 mColorSensor = new ColorSensorV3(i2cPort);
  private ColorMatch mColorMatcher = new ColorMatch();

  private String gameData;
  private String targetColor;
  private String detectedColorString;

  private final Color BLUE = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color GREEN = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color RED = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color YELLOW = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public PanelSpinnerMotor() {
    motor.configFactoryDefault();
    motor.setInverted(false);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TIMEOUT_MS);
    motor.setSensorPhase(false);
    motor.setNeutralMode(NeutralMode.Brake);

    motor.configPeakOutputForward(0.9);
    motor.configPeakOutputReverse(-0.9);
    motor.configClosedLoopPeakOutput(0, 0.9);

    motor.config_kF(0, 0.0, Constants.TIMEOUT_MS);
    motor.config_kP(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kI(0, 0.0, Constants.TIMEOUT_MS);
		motor.config_kD(0, 0.0, Constants.TIMEOUT_MS);

    mColorMatcher.addColorMatch(BLUE);
    mColorMatcher.addColorMatch(GREEN);
    mColorMatcher.addColorMatch(RED);
    mColorMatcher.addColorMatch(YELLOW);
  }

  @Override
  public void periodic() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B':
          setTargetColor("Blue");
          break;
        case 'G':
          setTargetColor("Green");
          break;
        case 'R':
          setTargetColor("Red");
          break;
        case 'Y':
          setTargetColor("Yellow");
          break;
        default:
          setTargetColor("Error");
          break;
      }
    }

    updateDetcectedColor();
  }

  public void setPercentOutput(double output) {
    if (output > 1.0) {
      output = 1.0;
    } else if (output < -1.0) {
      output = -1.0;
    }

    motor.set(output);
  }

  public void updateDetcectedColor() {
    Color detectedColor = mColorSensor.getColor();
    ColorMatchResult match = mColorMatcher.matchClosestColor(detectedColor);

    if (match.color == BLUE) {
      detectedColorString = "Blue";
    } else if (match.color == RED) {
      detectedColorString = "Red";
    } else if (match.color == GREEN) {
      detectedColorString = "Green";
    } else if (match.color == YELLOW) {
      detectedColorString = "Yellow";
    } else {
      detectedColorString = "Unknown";
    }

    SmartDashboard.putNumber("Red Value", detectedColor.red);
    SmartDashboard.putNumber("Green Value", detectedColor.green);
    SmartDashboard.putNumber("Blue Value", detectedColor.blue);
    SmartDashboard.putString("Detected Color", detectedColorString);
    SmartDashboard.putNumber("Confidence", match.confidence);
  }

  public boolean isGameDataPresent() {
    boolean isPresent;
    if (gameData.length() > 0) {
      isPresent = true;
    } else {
      isPresent = false;
    }

    return isPresent;
  }
  public String getDetectedColorString() {
    return detectedColorString;
  }

  public String setTargetColor(String color) {
    return targetColor = color;
  }

  public String getTargetColor() {
    return targetColor;
  }

  public void setSpinnerRotations(double rotations) {
    int rotationsInSRXUntis = (int) (rotations * 32 / 2 * Constants.COUNTS_PER_REVOLUTION_ENCODER);
    motor.set(ControlMode.Position, rotationsInSRXUntis);
  }

  public void resetEncoder() {
    motor.setSelectedSensorPosition(0);
  }
}
