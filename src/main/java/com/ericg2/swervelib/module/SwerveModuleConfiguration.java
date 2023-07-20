package com.ericg2.swervelib.module;

import com.ericg2.swervelib.math.AngularVelocity;
import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.math.GearRatio;
import com.ericg2.swervelib.math.Velocity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import java.util.function.Supplier;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

public class SwerveModuleConfiguration {
    private CANSparkMax driveMotor;
    private CANSparkMax turnMotor;

    private DutyCycleEncoder turnEncoder;

    private GearRatio driveRatio;
    private GearRatio turnRatio;

    private Rotation2d offset;
    private Velocity maxSpeed;

    private AngularVelocity maxTurnSpeed;

    private Distance wheelDiameter;

    private PIDController turnController = new PIDController(0.5, 0, 0);
    private PIDController driveController = new PIDController(0.5, 0, 0);

    private Supplier<Boolean> tuningModeSupplier = () -> false;
    private Supplier<Boolean> testModeSupplier = () -> true;

    private SwerveModuleSide moduleSide;

    private boolean motorInverted = false;

    public SwerveModuleConfiguration setDriveMotor(int motorID) {
        this.driveMotor = new CANSparkMax(motorID, kBrushless);
        return this;
    }

    public SwerveModuleConfiguration setTurnMotor(int motorID) {
        this.turnMotor = new CANSparkMax(motorID, kBrushless);
        return this;
    }

    public SwerveModuleConfiguration setTurnEncoder(DutyCycleEncoder encoder) {
        this.turnEncoder = encoder;
        return this;
    }

    public SwerveModuleConfiguration setDriveRatio(GearRatio ratio) {
        this.driveRatio = ratio;
        return this;
    }

    public SwerveModuleConfiguration setTurnRatio(GearRatio ratio) {
        this.turnRatio = ratio;
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

    public SwerveModuleConfiguration setTuningModeSupplier(Supplier<Boolean> tuningModeSupplier) {
        this.tuningModeSupplier = tuningModeSupplier;
        return this;
    }

    public SwerveModuleConfiguration setTestModeSupplier(Supplier<Boolean> testModeSupplier) {
        this.testModeSupplier = testModeSupplier;
        return this;
    }

    public CANSparkMax getDriveMotor() { return this.driveMotor; }
    public CANSparkMax getTurnMotor() { return this.turnMotor; }
    public DutyCycleEncoder getTurnEncoder() { return this.turnEncoder; }
    public GearRatio getDriveRatio() { return this.driveRatio; }
    public GearRatio getTurnRatio() { return this.turnRatio; }
    public Rotation2d getOffset() { return this.offset; }
    public PIDController getDriveController() { return this.driveController; }
    public PIDController getTurnController() { return this.turnController; }
    public Distance getWheelDiameter() { return this.wheelDiameter; }
    public Velocity getMaxVelocity() { return this.maxSpeed; }
    public SwerveModuleSide getModuleSide() { return this.moduleSide; }
    public Supplier<Boolean> getTuningModeSupplier() { return this.tuningModeSupplier; }
    public Supplier<Boolean> getTestModeSupplier() { return this.testModeSupplier; }
    public AngularVelocity getMaxTurnVelocity() { return this.maxTurnSpeed; }

    public boolean isMotorInverted() { return this.motorInverted; }

    public RelativeEncoder getDriveEncoder() {
        if (driveMotor == null)
            return null;
        try {
            return driveMotor.getEncoder();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean validate() {
        if (driveMotor == null)
            return false;
        if (turnMotor == null)
            return false;
        if (turnEncoder == null)
            return false;
        if (driveRatio == null)
            return false;
        if (turnRatio == null)
            return false;
        if (offset == null)
            return false;
        if (driveController == null)
            return false;
        if (turnController == null)
            return false;
        if (wheelDiameter == null)
            return false;
        if (maxSpeed == null)
            return false;
        if (moduleSide == null)
            return false;
        if (tuningModeSupplier == null)
            return false;
        if (testModeSupplier == null)
            return false;

        if (driveMotor.getMotorType() == kBrushed)
            return false;
        if (getDriveEncoder() == null)
            return false;
        if (wheelDiameter.toMeters() <= 0)
            return false;

        return moduleSide != SwerveModuleSide.NONE;
    }
}
