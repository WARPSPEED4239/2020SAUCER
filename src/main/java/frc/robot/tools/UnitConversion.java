package frc.robot.tools;

import frc.robot.Constants;

public class UnitConversion {
    public static double convertSRXUnitsToRotations(double units) {
        return units / Constants.COUNTS_PER_REVOLUTION_ENCODER;
    }

    public static double convertRotationsToSRXUnits(double rotations) {
        return rotations * Constants.COUNTS_PER_REVOLUTION_ENCODER;
    }

    public static double convertRotationsToInches(double rotations, double pulleyDiameter) {
        return rotations * Math.PI * pulleyDiameter;
    }

    public static double convertPositionInInchesToRotations(double positionInInches, double pulleyDiameter) {
        return positionInInches / (pulleyDiameter * Math.PI);
    }

    public static double convertSRXUnitsToDegrees(double units) {
        return (units / Constants.COUNTS_PER_REVOLUTION_ENCODER) * 360;
    }

    public static double convertPositionInDegreesToSRXUnits(double positionInDegrees) {
        return (positionInDegrees / 360) * Constants.COUNTS_PER_REVOLUTION_ENCODER;
    }
}