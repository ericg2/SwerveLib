package com.ericg2.swervelib;

import com.ericg2.swervelib.chassis.SwerveChassisConfiguration;
import com.ericg2.swervelib.math.*;
import com.ericg2.swervelib.module.SwerveModule;
import com.ericg2.swervelib.module.SwerveModuleConfiguration;
import com.ericg2.swervelib.module.SwerveModuleSide;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

public class Constants {
    public static class Chassis {
        public static GearRatio DRIVE_RATIO = GearRatio.fromRatio(6.86);
        public static GearRatio TURN_RATIO = GearRatio.fromRatio(12.8);
        public static Distance WHEEL_SIZE = Distance.fromInches(4);
        public static Distance SIDE_LENGTH = Distance.fromInches(26);

        public static SwerveVelocities MAX_VELOCITY = new SwerveVelocities(
                Velocity.fromMPS(4.4),
                Velocity.fromMPS(4.4),
                AngularVelocity.fromRPM(4000)
        );

        public static PIDController DRIVE_CONTROLLER = new PIDController(5, 0, 0.01);
        public static PIDController TURN_CONTROLLER = new PIDController(5, 0, 0.01);

        public static SwerveModuleConfiguration FL_CONFIG = new SwerveModuleConfiguration(
                1,
                2,
                0,
                DRIVE_RATIO,
                TURN_RATIO,
                Rotation2d.fromDegrees(181.45),
                DRIVE_CONTROLLER,
                TURN_CONTROLLER,
                WHEEL_SIZE,
                SwerveModuleSide.FRONT_LEFT,
                MAX_VELOCITY
        );

        public static SwerveModuleConfiguration FR_CONFIG = new SwerveModuleConfiguration(
                3,
                4,
                1,
                DRIVE_RATIO,
                TURN_RATIO,
                Rotation2d.fromDegrees(-226.32),
                DRIVE_CONTROLLER,
                TURN_CONTROLLER,
                WHEEL_SIZE,
                SwerveModuleSide.FRONT_RIGHT,
                MAX_VELOCITY
        );

        public static SwerveModuleConfiguration BL_CONFIG = new SwerveModuleConfiguration(
                5,
                6,
                2,
                DRIVE_RATIO,
                TURN_RATIO,
                Rotation2d.fromDegrees(12.71),
                DRIVE_CONTROLLER,
                TURN_CONTROLLER,
                WHEEL_SIZE,
                SwerveModuleSide.BACK_LEFT,
                MAX_VELOCITY
        );

        public static SwerveModuleConfiguration BR_CONFIG = new SwerveModuleConfiguration(
                7,
                8,
                3,
                DRIVE_RATIO,
                TURN_RATIO,
                Rotation2d.fromDegrees(169.38),
                DRIVE_CONTROLLER,
                TURN_CONTROLLER,
                WHEEL_SIZE,
                SwerveModuleSide.BACK_RIGHT,
                MAX_VELOCITY
        );

        public static SwerveChassisConfiguration CHASSIS_CONFIG = new SwerveChassisConfiguration(
                SIDE_LENGTH,
                new AHRS(SPI.Port.kMXP),
                FL_CONFIG,
                FR_CONFIG,
                BL_CONFIG,
                BR_CONFIG,
                false
        );
    }
}
