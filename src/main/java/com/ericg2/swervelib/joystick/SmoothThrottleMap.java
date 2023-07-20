package com.ericg2.swervelib.joystick;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class SmoothThrottleMap implements JoystickThrottleMap {
    @Override public double getX(double xVel) { return Math.pow(xVel, 3); }
    @Override public double getY(double yVel) { return Math.pow(yVel, 3); }
    @Override public double getTwist(double twistVel) { return Math.pow(twistVel, 3); }
    @Override public SlewRateLimiter getRateLimiter() { return DEFAULT_LIMIT; }
    @Override public String toString() { return "Smooth"; }
}
