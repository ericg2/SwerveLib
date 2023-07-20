package com.ericg2.swervelib;

import com.ericg2.swervelib.chassis.SwerveChassisConfiguration;
import com.ericg2.swervelib.chassis.SwerveDriveSubsystem;
import com.ericg2.swervelib.exception.InvalidConfigurationException;
import com.ericg2.swervelib.joystick.DriveXboxController;
import com.ericg2.swervelib.joystick.SmoothThrottleMap;
import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.math.ExtendedMath;
import com.ericg2.swervelib.math.GearRatio;
import com.ericg2.swervelib.math.SwerveVelocities;
import com.ericg2.swervelib.module.SwerveModule;
import com.ericg2.swervelib.module.SwerveModuleConfiguration;
import com.ericg2.swervelib.module.SwerveModuleSide;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.I2C;

public class RobotContainer {
    public RobotContainer() throws InvalidConfigurationException {
        DriveXboxController xbox = new DriveXboxController(0)
                .setForwardAxisInverted(true)
                .setSideAxisInverted(true)
                .setTwistAxisInverted(true)
                .setThrottleMap(new SmoothThrottleMap())
                .setTCSEnabled(true);

        GearRatio driveRatio = GearRatio.fromRatio(6.86);
        GearRatio turnRatio = GearRatio.fromRatio(12.8);
        Distance wheelSize = Distance.fromInches(4);

        SwerveVelocities maxVelocities = ExtendedMath.calculateMaxSpeeds(
                driveRatio,
                turnRatio,
                wheelSize,
                5676,
                5676
        );

        SwerveModule flModule = new SwerveModule(
                new SwerveModuleConfiguration()
                        .setModuleSide(SwerveModuleSide.FRONT_LEFT)
                        .setOffset(Rotation2d.fromDegrees(0))
                        .setDriveRatio(GearRatio.fromRatio(8.16))
                        .setTurnRatio(GearRatio.fromRatio(12.8))
                        .setDriveMotor(1)
                        .setTurnMotor(2)
                        .setMotorInverted(false)
                        .setWheelDiameter(wheelSize)
                        .setMaxVelocity(maxVelocities.getForwardVelocity())
                        .setMaxTurnVelocity(maxVelocities.getTwistVelocity())
        );

        SwerveModule frModule = new SwerveModule(
                new SwerveModuleConfiguration()
                        .setModuleSide(SwerveModuleSide.FRONT_RIGHT)
                        .setOffset(Rotation2d.fromDegrees(0))
                        .setDriveRatio(driveRatio)
                        .setTurnRatio(turnRatio)
                        .setDriveMotor(3)
                        .setTurnMotor(4)
                        .setMotorInverted(false)
                        .setWheelDiameter(wheelSize)
                        .setMaxVelocity(maxVelocities.getForwardVelocity())
                        .setMaxTurnVelocity(maxVelocities.getTwistVelocity())
        );

        SwerveModule blModule = new SwerveModule(
                new SwerveModuleConfiguration()
                        .setModuleSide(SwerveModuleSide.BACK_LEFT)
                        .setOffset(Rotation2d.fromDegrees(0))
                        .setDriveRatio(driveRatio)
                        .setTurnRatio(turnRatio)
                        .setDriveMotor(5)
                        .setTurnMotor(6)
                        .setMotorInverted(false)
                        .setWheelDiameter(wheelSize)
                        .setMaxVelocity(maxVelocities.getForwardVelocity())
                        .setMaxTurnVelocity(maxVelocities.getTwistVelocity())
        );

        SwerveModule brModule = new SwerveModule(
                new SwerveModuleConfiguration()
                        .setModuleSide(SwerveModuleSide.BACK_RIGHT)
                        .setOffset(Rotation2d.fromDegrees(0))
                        .setDriveRatio(driveRatio)
                        .setTurnRatio(turnRatio)
                        .setDriveMotor(7)
                        .setTurnMotor(8)
                        .setMotorInverted(false)
                        .setWheelDiameter(wheelSize)
                        .setMaxVelocity(maxVelocities.getForwardVelocity())
                        .setMaxTurnVelocity(maxVelocities.getTwistVelocity())
        );

        SwerveDriveSubsystem swerve = new SwerveDriveSubsystem(
                new SwerveChassisConfiguration()
                    .setFrontLeftModule(flModule)
                    .setFrontRightModule(frModule)
                    .setBackLeftModule(blModule)
                    .setBackRightModule(brModule)
                    .setGyro(new AHRS(I2C.Port.kMXP))
                    .setGyroInverted(true)
                    .setSideLength(Distance.fromInches(26))
        );

        swerve.run(() -> {
            // drive normally
            swerve.drive(xbox, false, true);
        });
    }
}
