package com.ericg2.swervelib.joystick;

import com.ericg2.swervelib.math.AngularVelocity;
import com.ericg2.swervelib.math.Velocity;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;

public class DriveXboxController extends XboxController implements DriveController {
    private JoystickThrottleMap throttleMap;
    private boolean tcsEnabled;

    private boolean sideAxisInverted;
    private boolean forwardAxisInverted;
    private boolean twistAxisInverted;

    public DriveXboxController setThrottleMap(JoystickThrottleMap map) {
        this.throttleMap = map;
        return this;
    }

    public DriveXboxController setTCSEnabled(boolean tcsEnabled) {
        this.tcsEnabled = tcsEnabled;
        return this;
    }

    public DriveXboxController setForwardAxisInverted(boolean inverted) {
        this.forwardAxisInverted = inverted;
        return this;
    }

    public DriveXboxController setSideAxisInverted(boolean inverted) {
        this.sideAxisInverted = inverted;
        return this;
    }

    public DriveXboxController setTwistAxisInverted(boolean inverted) {
        this.twistAxisInverted = inverted;
        return this;
    }

    public JoystickThrottleMap getThrottleMap() { return this.throttleMap; }
    public boolean isTCSEnabled() { return this.tcsEnabled; }
    public boolean isForwardAxisInverted() { return this.forwardAxisInverted; }
    public boolean isSideAxisInverted() { return this.sideAxisInverted; }
    public boolean isTwistAxisInverted() { return this.twistAxisInverted; }

    public DriveXboxController(int port) {
        super(port);
        this.throttleMap = new LinearThrottleMap();
        this.tcsEnabled = true;
        this.sideAxisInverted = true;
        this.forwardAxisInverted = true;
        this.twistAxisInverted = false;
    }

    @Override
    public double getLeftX() {
        double val = super.getLeftX();
        val = throttleMap.getX(val);

        if (tcsEnabled) {
            SlewRateLimiter limiter = throttleMap.getRateLimiter();
            if (limiter != null)
                val = limiter.calculate(val);
        }

        return (sideAxisInverted) ? -val : val;
    }
    @Override
    public double getLeftY() {
        double val = super.getLeftY();
        val = throttleMap.getY(val);

        if (tcsEnabled) {
            SlewRateLimiter limiter = throttleMap.getRateLimiter();
            if (limiter != null)
                val = limiter.calculate(val);
        }

        return (forwardAxisInverted) ? -val : val;
    }

    @Override
    public double getRightX() {
        double val = super.getRightX();
        val = throttleMap.getTwist(val);

        if (tcsEnabled) {
            SlewRateLimiter limiter = throttleMap.getRateLimiter();
            if (limiter != null)
                val = limiter.calculate(val);
        }

        return (twistAxisInverted) ? -val : val;
    }

    @Override
    public Velocity getForwardVelocity(Velocity max) {
        return Velocity.fromMotorPower(getLeftY(), max);
    }

    @Override
    public Velocity getSideVelocity(Velocity max) {
        return Velocity.fromMotorPower(getLeftX(), max);
    }

    @Override
    public AngularVelocity getTwistVelocity(AngularVelocity max) {
        return AngularVelocity.fromMotorPower(getRightX(), max);
    }
}
