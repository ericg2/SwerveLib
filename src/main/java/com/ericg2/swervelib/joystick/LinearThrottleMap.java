package com.ericg2.swervelib.joystick;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class LinearThrottleMap implements JoystickThrottleMap {
    @Override public double getX(double xVel) { return xVel; }
    @Override public double getY(double yVel) { return yVel; }
    @Override public double getTwist(double twistVel) { return twistVel; }
    @Override public SlewRateLimiter getRateLimiter() { return DEFAULT_LIMIT; }
    @Override public String toString() { return "Linear"; }
}
