package com.ericg2.swervelib.math;

public class GearRatio {

    private final double ratio;
    public GearRatio(double ratio) {
        this.ratio = ratio;
    }

    public double degreesToMotorRotations(Angle angle) {
        return (ratio / 360) * angle.getValue(AngleUnit.DEGREES);
    }

    public Angle motorRotationsToAngle(double rotations) {
        return Angle.fromValue((360 / ratio) * rotations, AngleUnit.DEGREES);
    }

    public Velocity getWheelSpeed(double motorRotations, Distance wheelDiameter) {
        double diameter = wheelDiameter.getValue(DistanceUnit.METERS);

        // convert motor rotations to degrees
        return Velocity.fromValue((2 * (Math.PI * (diameter / 2))) * ((motorRotations * ratio) / 60), VelocityUnit.MPS);
    }

    public Distance getWheelDistance(double motorRotations, Distance wheelDiameter) {
        double diameter = wheelDiameter.getValue(DistanceUnit.METERS);

        return Distance.fromValue((2 * (Math.PI * (diameter / 2))) * ((motorRotations * ratio)), DistanceUnit.METERS);
    }


    public static GearRatio fromRatio(double ratio) {
        return new GearRatio(ratio);
    }

    public static double angleToMotorRotations(Angle angle, double ratio) {
        return (ratio / 360) * angle.getValue(AngleUnit.DEGREES);
    }

    public static Angle motorRotationsToAngle(double rotations, double ratio) {
        return Angle.fromValue((360 / ratio) * rotations, AngleUnit.DEGREES);
    }

    public static Velocity getWheelSpeed(double motorRotations, double wheelRadius, double ratio) {
        return Velocity.fromValue((2 * (Math.PI * wheelRadius)) * ((motorRotations * ratio) / 60), VelocityUnit.MPS);
    }
}
