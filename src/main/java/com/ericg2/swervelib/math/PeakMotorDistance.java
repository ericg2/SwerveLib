package com.ericg2.swervelib.math;

public class PeakMotorDistance {
    private final Distance maxDistance;
    private final double maxRotation;

    public PeakMotorDistance(Distance distance, double rotation) {
        assert rotation != 0;
        this.maxDistance = distance;
        this.maxRotation = rotation;
    }

    public Distance getDistance() {
        return maxDistance;
    }

    public double getRotation() {
        return maxRotation;
    }

    /** @return rotation to distance based on unit */
    public double rotationToDistance(double currentRotation) {
        return (currentRotation / maxRotation) * maxDistance.getValue();
    }

    public double distanceToRotation(double currentDistance) {
        return (currentDistance / maxDistance.getValue()) * maxRotation;
    }
}