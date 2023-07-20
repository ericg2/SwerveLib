package com.ericg2.swervelib.joystick;

import com.ericg2.swervelib.math.AngularVelocity;
import com.ericg2.swervelib.math.Velocity;

public interface DriveController {
    Velocity getForwardVelocity(Velocity max);
    Velocity getSideVelocity(Velocity max);
    AngularVelocity getTwistVelocity(AngularVelocity max);
}
