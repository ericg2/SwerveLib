package com.ericg2.swervelib;

import com.ericg2.swervelib.exception.InvalidConfigurationException;
import com.ericg2.swervelib.math.*;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link SwerveModule} is composed of two motors and two encoders:
 * a drive motor/encoder and a turn motor/encoder. The turn motor is
 * responsible for controlling the direction the drive motor faces, essentially
 * allowing the robot to move in any direction.
 */
public class SwerveModule {
    private final SwerveModuleConfiguration config;

    /**
     * Creates a new {@link SwerveModule} instance, using the specified parameters.
     */
    public SwerveModule(SwerveModuleConfiguration config) throws InvalidConfigurationException {
        if (config == null || !config.validate()) {
            throw new InvalidConfigurationException("Swerve Module Config is invalid!");
        }
        this.config = config;
    }

    public Velocity getDriveVelocity() {
        return config.getDriveRatio().getWheelSpeed(config.getDriveEncoder().getVelocity(), config.getWheelDiameter())
    }

    public Angle getTurnRotation() {
        Angle angleDegNoOffset = config.getTurnRatio().motorRotationsToAngle(config.getTurnEncoder().get());
        return angleDegNoOffset.Add(config.getOffset());
    }

    public void setState(SwerveModuleState state, boolean isClosedLoop) {
        state = SwerveModuleState.optimize(state, getTurnRotation().toRotation2d());

        double turnPower = config.getTurnController().calculate(
                getTurnRotation().getDegrees(),
                state.angle.getDegrees()
        );

        double drivePower;
        if (isClosedLoop) {
            drivePower = config.getDriveController().calculate(
                    getDriveVelocity().getValue(VelocityUnit.MPS),
                    state.speedMetersPerSecond
            );
        } else {
            drivePower = Velocity.fromValue(state.speedMetersPerSecond, VelocityUnit.MPS)
                    .toMotorPower(config.getMaxSpeed());
        }

        config.getDriveMotor().set(drivePower);
        config.getTurnMotor().set(turnPower);
    }

    /**
     * Get the {@link SwerveModuleState} based on the drive motor's velocity
     * (meters/sec) and the turn encoder's angle.
     *
     * @return a new {@link SwerveModuleState}, representing the module's
     * current state, based on the module's drive motor velocity (m/s)
     * and the turn encoder's angle.
     */
    public SwerveModuleState getState() {
        return new SwerveModuleState(
                getDriveVelocity().getValue(VelocityUnit.MPS),
                getTurnRotation().toRotation2d()
        );
    }

    /**
     * Get the {@link SwerveModulePosition} based on the drive motor's
     * distance travelled (in meters), and turn encoder's angle. This
     * is required for {@link SwerveOdometry} to work correctly.
     *
     * @return A {@link SwerveModulePosition}, representing the module's
     * current position, based on the module's drive motor distance and
     * the turn encoder's angle.
     */
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
                g
                getTurnAngle()
        );
    }

    public void updateDashboard(String prefix) {
        String driveVelocity = prefix + ": rpm";
        String drivePower = prefix + ": pow";
        String turnPower = prefix + ": turn pow";
        String turnPosition = prefix + ": turn rad";



        if (TEST_MODE) {
            SmartDashboard.putNumber(driveVelocity, getRPM());
            SmartDashboard.putNumber(turnPower, turnMotor.get());
            SmartDashboard.putNumber(turnPosition, turnAngleRadians());
            SmartDashboard.putNumber(drivePower, driveMotor.get());
            SmartDashboard.putNumber(prefix + " offset tuning rad:", turnAngleRadiansNoOffset());
            SmartDashboard.putNumber(prefix + " drive encoder: ", driveEncoder.getPosition());
        }

    }


    public Distance getDistanceDriven() {
        return config.getDriveEncoder().getPosition()
    }

    public void resetDriveEncoder() {
        driveEncoder.setPosition(0);
    }
}