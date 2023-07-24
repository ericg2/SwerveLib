package com.ericg2.swervelib.module;

import com.ericg2.swervelib.math.*;
import com.ericg2.swervelib.sim.AutoDutyCycleEncoder;
import com.ericg2.swervelib.sim.AutoSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

public class SwerveModuleConfiguration {
    private final AutoSparkMax driveMotor;
    private final AutoSparkMax turnMotor;

    private final AutoDutyCycleEncoder turnEncoder;

    private final GearRatio driveRatio;
    private final GearRatio turnRatio;

    private final Rotation2d offset;
    private final Velocity maxSpeed;

    private final AngularVelocity maxTurnSpeed;

    private final Distance wheelDiameter;

    private final PIDController turnController;
    private final PIDController driveController;

    private final SwerveModuleSide moduleSide;

    private final boolean driveInverted, turnInverted;

    /*
    public SwerveModuleConfiguration setDriveInfo(int motorID, GearRatio driveRatio) {
        this.driveMotor = new AutoSparkMax(motorID, kBrushless);
        this.driveRatio = driveRatio;
        return this;
    }

    public SwerveModuleConfiguration setTurnInfo(int motorID, int encoderID, GearRatio turnRatio) {
        this.turnMotor = new AutoSparkMax(motorID, kBrushless);
        this.turnEncoder = new AutoDutyCycleEncoder(encoderID, turnMotor, turnRatio);
        this.turnRatio = turnRatio;
        return this;
    }

    public SwerveModuleConfiguration setOffset(Rotation2d offset) {
        this.offset = offset;
        return this;
    }

    public SwerveModuleConfiguration setMaxVelocity(Velocity velocity) {
        this.maxSpeed = velocity;
        return this;
    }

    public SwerveModuleConfiguration setMotorInverted(boolean inverted) {
        this.motorInverted = inverted;
        return this;
    }

    public SwerveModuleConfiguration setDriveController(PIDController driveController) {
        this.driveController = driveController;
        return this;
    }

    public SwerveModuleConfiguration setTurnController(PIDController turnController) {
        this.turnController = turnController;
        return this;
    }

    public SwerveModuleConfiguration setMaxTurnVelocity(AngularVelocity velocity) {
        this.maxTurnSpeed = velocity;
        return this;
    }

    public SwerveModuleConfiguration setWheelDiameter(Distance wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
        return this;
    }

    public SwerveModuleConfiguration setModuleSide(SwerveModuleSide moduleSide) {
        this.moduleSide = moduleSide;
        return this;
    }
     */

    public SwerveModuleConfiguration(
            int turnMotorID,
            int driveMotorID,
            int turnEncoderID,
            GearRatio driveRatio,
            GearRatio turnRatio,
            Rotation2d offset,
            PIDController driveController,
            PIDController turnController,
            Distance wheelDiameter,
            SwerveModuleSide moduleSide,
            SwerveVelocities maxVelocities,
            boolean driveInverted,
            boolean turnInverted
    ) {
        this.driveRatio = driveRatio;
        this.turnRatio = turnRatio;
        this.turnMotor = new AutoSparkMax(turnMotorID, kBrushless);
        this.driveMotor = new AutoSparkMax(driveMotorID, kBrushless);
        this.turnEncoder = new AutoDutyCycleEncoder(turnEncoderID, turnMotor, turnRatio);
        this.offset = offset;
        this.driveController = driveController;
        this.turnController = turnController;
        this.wheelDiameter = wheelDiameter;
        this.moduleSide = moduleSide;
        this.maxSpeed = maxVelocities.getForwardVelocity();
        this.maxTurnSpeed = maxVelocities.getTwistVelocity();
        this.driveInverted = driveInverted;
        this.turnInverted = turnInverted;

        driveMotor.setInverted(driveInverted);
        turnMotor.setInverted(turnInverted);
    }

    public SwerveModuleConfiguration(
            int turnMotorID,
            int driveMotorID,
            int turnEncoderID,
            GearRatio driveRatio,
            GearRatio turnRatio,
            Rotation2d offset,
            PIDController driveController,
            PIDController turnController,
            Distance wheelDiameter,
            SwerveModuleSide moduleSide,
            SwerveVelocities maxVelocities
    ) {
        this(
                turnMotorID,
                driveMotorID,
                turnEncoderID,
                driveRatio,
                turnRatio,
                offset,
                driveController,
                turnController,
                wheelDiameter,
                moduleSide,
                maxVelocities,
                false,
                false
        );
    }

    public AutoSparkMax getDriveMotor() { return this.driveMotor; }
    public AutoSparkMax getTurnMotor() { return this.turnMotor; }
    public AutoDutyCycleEncoder getTurnEncoder() { return this.turnEncoder; }
    public GearRatio getDriveRatio() { return this.driveRatio; }
    public GearRatio getTurnRatio() { return this.turnRatio; }
    public Rotation2d getOffset() { return this.offset; }
    public PIDController getDriveController() { return this.driveController; }
    public PIDController getTurnController() { return this.turnController; }
    public Distance getWheelDiameter() { return this.wheelDiameter; }
    public Velocity getMaxVelocity() { return this.maxSpeed; }
    public SwerveModuleSide getModuleSide() { return this.moduleSide; }
    public AngularVelocity getMaxTurnVelocity() { return this.maxTurnSpeed; }
    public RelativeEncoder getDriveEncoder() { return driveMotor.getEncoder(); }

    public boolean isDriveInverted() { return this.driveInverted; }
    public boolean isTurnInverted() { return this.turnInverted; }
}
