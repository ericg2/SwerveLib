package com.ericg2.swervelib.math;

import edu.wpi.first.math.geometry.Rotation2d;

public class GearRatio {

    private final double ratio;
    public GearRatio(double ratio) {
        this.ratio = ratio;
    }

    public double angleToMotorRotations(Rotation2d angle) {
        return (ratio / 360) * angle.getDegrees();
    }

    public Rotation2d motorRotationsToAngle(double rotations) {
        return Rotation2d.fromDegrees((360 / ratio) * rotations);
    }

    public Velocity getWheelVelocity(double motorRotations, Distance wheelDiameter) {
        double diameter = wheelDiameter.toMeters();

        // convert motor rotations to degrees
        return Velocity.fromValue((2 * (Math.PI * (diameter / 2))) * ((motorRotations * ratio) / 60), VelocityUnit.MPS);
    }

    public Distance getWheelDistance(double motorRotations, Distance wheelDiameter) {
        double diameter = wheelDiameter.toMeters();

        return Distance.fromValue((2 * (Math.PI * (diameter / 2))) * ((motorRotations * ratio)), DistanceUnit.METERS);
    }


    public static GearRatio fromRatio(double ratio) {
        return new GearRatio(ratio);
    }

    public static double angleToMotorRotations(Rotation2d angle, double ratio) {
        return (ratio / 360) * angle.getDegrees();
    }

    public static Rotation2d motorRotationsToAngle(double rotations, double ratio) {
        return Rotation2d.fromDegrees((360 / ratio) * rotations);
    }

    public static Velocity getWheelVelocity(double motorRotations, double wheelRadius, double ratio) {
        return Velocity.fromValue((2 * (Math.PI * wheelRadius)) * ((motorRotations * ratio) / 60), VelocityUnit.MPS);
    }
}
