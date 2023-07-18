package com.ericg2.swervelib.math;

public class Velocity {
    private double metersPerSecondValue;
    private VelocityUnit unit;

    public Velocity(double value, VelocityUnit unit) {
        this.metersPerSecondValue = value;
        this.unit = unit;
    }

    public Velocity setSpeed(double value, VelocityUnit unit) {
        this.metersPerSecondValue = convert(value, unit, VelocityUnit.MPS);
        this.unit = unit;
        return this;
    }

    public double toMotorPower(Velocity maxSpeed) {
        return getValue(VelocityUnit.MPS) / maxSpeed.getValue(VelocityUnit.MPS);
    }

    public VelocityUnit getSpeedUnit() {
        return this.unit;
    }

    public double getValue(VelocityUnit unit) {
        return convert(metersPerSecondValue, VelocityUnit.MPS, unit);
    }

    public double getValue() {
        return convert(metersPerSecondValue, VelocityUnit.MPS, this.unit);
    }

    public static Velocity fromValue(double value, VelocityUnit unit) {
        return new Velocity(value, unit);
    }

    public static double convert(double value, VelocityUnit oldUnit, VelocityUnit newUnit) {
        switch (oldUnit) {
            case KPH: {
                switch (newUnit) {
                    case KPH: return value;
                    case MPH: return value / 1.609;
                    case MPS: return value / 3.60;
                }
            }
            case MPH: {
                switch (newUnit) {
                    case KPH: return value * 1.609;
                    case MPH: return value;
                    case MPS: return value / 2.237;
                }
            }
            case MPS: {
                switch (newUnit) {
                    case KPH: return value * 3.60;
                    case MPH: return value * 2.237;
                    case MPS: return value;
                }
            }
            default: return value;
        }
    }
}
