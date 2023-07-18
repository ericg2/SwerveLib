package com.ericg2.swervelib.math;

import edu.wpi.first.math.geometry.Rotation2d;

public class Angle {
    private double radianValue;
    private AngleUnit unit;

    public Angle(double value, AngleUnit unit) {
        this.radianValue = value;
        this.unit = unit;
    }

    public Angle setRotation(double value, AngleUnit unit) {
        radianValue = convert(value, unit, AngleUnit.RADIANS);
        this.unit = unit;
        return this;
    }

    public Angle Add(Angle angle) {
        this.radianValue += angle.getValue(AngleUnit.RADIANS);
        return this;
    }

    public Angle Subtract(Angle angle) {
        this.radianValue -= angle.getValue(AngleUnit.RADIANS);
        return this;
    }

    public AngleUnit getRotationUnit() {
        return this.unit;
    }

    public double getValue(AngleUnit unit) {
        return convert(radianValue, AngleUnit.RADIANS, unit);
    }

    public static Angle fromValue(double value, AngleUnit unit) {
        return new Angle(value, unit);
    }

    public double getDegrees() {
        return getValue(AngleUnit.DEGREES);
    }

    public double getRadians() {
        return getValue(AngleUnit.RADIANS);
    }

    public Rotation2d toRotation2d() {
        return new Rotation2d(getRadians());
    }

    public double getValue() {
        return convert(radianValue, AngleUnit.RADIANS, this.unit);
    }

    public static double convert(double value, AngleUnit oldUnit, AngleUnit newUnit) {
        switch (oldUnit) {
            case DEGREES: {
                switch (newUnit) {
                    case RADIANS: return 1 * (Math.PI/180);
                    case DEGREES: return value;
                }
            }
            case RADIANS: {
                switch (newUnit) {
                    case RADIANS: return value;
                    case DEGREES: return 1 * (180/Math.PI);
                }
            }
            default: return value;
        }
    }
}
