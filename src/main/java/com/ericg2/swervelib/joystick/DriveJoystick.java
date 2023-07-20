package com.ericg2.swervelib.joystick;


import com.ericg2.swervelib.math.AngularVelocity;
import com.ericg2.swervelib.math.AngularVelocityUnit;
import com.ericg2.swervelib.math.Velocity;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

public class DriveJoystick extends CommandJoystick implements DriveController {
    private JoystickThrottleMap throttleMap;
    private boolean tcsEnabled;

    private boolean sideAxisInverted;
    private boolean forwardAxisInverted;
    private boolean twistAxisInverted;

    public DriveJoystick setThrottleMap(JoystickThrottleMap map) {
        this.throttleMap = map;
        return this;
    }

    public DriveJoystick setTCSEnabled(boolean tcsEnabled) {
        this.tcsEnabled = tcsEnabled;
        return this;
    }

    public DriveJoystick setForwardAxisInverted(boolean inverted) {
        this.forwardAxisInverted = inverted;
        return this;
    }

    public DriveJoystick setSideAxisInverted(boolean inverted) {
        this.sideAxisInverted = inverted;
        return this;
    }

    public DriveJoystick setTwistAxisInverted(boolean inverted) {
        this.twistAxisInverted = inverted;
        return this;
    }

    public JoystickThrottleMap getThrottleMap() { return this.throttleMap; }
    public boolean isTCSEnabled() { return this.tcsEnabled; }
    public boolean isForwardAxisInverted() { return this.forwardAxisInverted; }
    public boolean isSideAxisInverted() { return this.sideAxisInverted; }
    public boolean isTwistAxisInverted() { return this.twistAxisInverted; }

    public DriveJoystick(int port) {
        super(port);
        this.throttleMap = new LinearThrottleMap();
        this.tcsEnabled = true;
        this.sideAxisInverted = true;
        this.forwardAxisInverted = true;
        this.twistAxisInverted = false;
    }

    @Override
    public double getX() {
        double val = super.getX();
        val = throttleMap.getX(val);

        if (tcsEnabled) {
            SlewRateLimiter limiter = throttleMap.getRateLimiter();
            if (limiter != null)
                val = limiter.calculate(val);
        }

        return (sideAxisInverted) ? -val : val;
    }

    @Override
    public double getY() {
        double val = super.getY();
        val = throttleMap.getY(val);

        if (tcsEnabled) {
            SlewRateLimiter limiter = throttleMap.getRateLimiter();
            if (limiter != null)
                val = limiter.calculate(val);
        }

        return (forwardAxisInverted) ? -val : val;
    }

    @Override
    public double getTwist() {
        double val = super.getTwist();
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
        return Velocity.fromMotorPower(getY(), max);
    }

    @Override
    public Velocity getSideVelocity(Velocity max) {
        return Velocity.fromMotorPower(getX(), max);
    }

    @Override
    public AngularVelocity getTwistVelocity(AngularVelocity max) {
        return AngularVelocity.fromMotorPower(getTwist(), max);
    }
}
