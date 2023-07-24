// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.ericg2.swervelib;

import com.ericg2.swervelib.chassis.SwerveDriveSubsystem;
import com.ericg2.swervelib.exception.InvalidConfigurationException;

import com.ericg2.swervelib.joystick.DriveXboxController;
import com.ericg2.swervelib.joystick.SmoothThrottleMap;
import com.ericg2.swervelib.math.AngularVelocity;
import com.ericg2.swervelib.math.GearRatio;
import com.ericg2.swervelib.math.SwerveVelocities;
import com.ericg2.swervelib.math.Velocity;
import com.ericg2.swervelib.sim.AutoDutyCycleEncoder;
import com.ericg2.swervelib.sim.AutoRelativeEncoder;
import com.ericg2.swervelib.sim.AutoSparkMax;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.server.PathPlannerServer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;

import static com.ericg2.swervelib.RobotContainer.swerve;
import static com.ericg2.swervelib.RobotContainer.xbox;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static SwerveDriveSubsystem swerve;

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotInit() {
        PathPlannerServer.startServer(5811);


    }

    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }
}
