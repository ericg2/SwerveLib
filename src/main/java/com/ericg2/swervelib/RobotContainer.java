package com.ericg2.swervelib;

import com.ericg2.swervelib.joystick.DriveJoystick;
import com.ericg2.swervelib.joystick.SmoothThrottleMap;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private static final SmoothThrottleMap throttleMap = new SmoothThrottleMap();

    public static DriveJoystick xyStick = new DriveJoystick(0, true, true, true, throttleMap, true);
    public static DriveJoystick zStick = new DriveJoystick(1, true, true, true, throttleMap, true);
    public static CommandXboxController xbox = new CommandXboxController(2);

    public RobotContainer() {
        Robot.swerve.setDefaultCommand(Robot.swerve.run(() ->
                Robot.swerve.drive(xyStick, zStick)));
        configureBindings();
    }

    private void configureBindings() {
        xbox.leftBumper().onTrue(Commands.runOnce(()->Robot.swerve.toggleClosedLoop()));
        xbox.rightBumper().onTrue(Commands.runOnce(()->Robot.swerve.toggleFieldOriented()));
    }
}
