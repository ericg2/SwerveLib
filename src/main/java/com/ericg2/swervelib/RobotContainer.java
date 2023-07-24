package com.ericg2.swervelib;

import com.ericg2.swervelib.chassis.SwerveChassisConfiguration;
import com.ericg2.swervelib.chassis.SwerveDriveSubsystem;
import com.ericg2.swervelib.exception.InvalidConfigurationException;
import com.ericg2.swervelib.joystick.DriveJoystick;
import com.ericg2.swervelib.joystick.DriveXboxController;
import com.ericg2.swervelib.joystick.SmoothThrottleMap;
import com.ericg2.swervelib.math.*;
import com.ericg2.swervelib.module.SwerveModule;
import com.ericg2.swervelib.module.SwerveModuleConfiguration;
import com.ericg2.swervelib.module.SwerveModuleSide;
import com.ericg2.swervelib.sim.AutoDutyCycleEncoder;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private static SmoothThrottleMap throttleMap = new SmoothThrottleMap();

    public static SwerveDriveSubsystem swerve;

    public static DriveJoystick xyStick = new DriveJoystick(0, true, true, true, throttleMap, true);
    public static DriveJoystick zStick = new DriveJoystick(1, true, true, true, throttleMap, true);
    public static CommandXboxController xbox = new CommandXboxController(2);

    public RobotContainer() { {
    }
}
