package com.ericg2.swervelib;

import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.math.GearRatio;
import com.ericg2.swervelib.math.Angle;
import com.ericg2.swervelib.math.Velocity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

public class SwerveModuleConfiguration {
    private CANSparkMax driveMotor;
    private CANSparkMax turnMotor;

    private DutyCycleEncoder turnEncoder;

    private GearRatio driveRatio;
    private GearRatio turnRatio;

    private Angle offset;
    private Velocity maxSpeed;

    private Distance wheelDiameter;

    private PIDController turnController = new PIDController(0.5, 0, 0);
    private PIDController driveController = new PIDController(0.5, 0, 0);

    public SwerveModuleConfiguration setDriveMotor(CANSparkMax motor) {
        this.driveMotor = motor;
        return this;
    }

    public SwerveModuleConfiguration setTurnMotor(CANSparkMax motor) {
        this.turnMotor = motor;
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

    public SwerveModuleConfiguration setOffset(Angle offset) {
        this.offset = offset;
        return this;
    }

    public SwerveModuleConfiguration setMaxSpeed(Velocity velocity) {
        this.maxSpeed = velocity;
        return this;
    }

    public SwerveModuleConfiguration setDriveController(PIDController driveController) {
        this.driveController = driveController;
        return this;
    }

    public SwerveModuleConfiguration setWheelDiameter(Distance wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
        return this;
    }

    public CANSparkMax getDriveMotor() { return this.driveMotor; }
    public CANSparkMax getTurnMotor() { return this.turnMotor; }
    public DutyCycleEncoder getTurnEncoder() { return this.turnEncoder; }
    public GearRatio getDriveRatio() { return this.driveRatio; }
    public GearRatio getTurnRatio() { return this.turnRatio; }
    public Angle getOffset() { return this.offset; }
    public PIDController getDriveController() { return this.driveController; }
    public PIDController getTurnController() { return this.turnController; }
    public Distance getWheelDiameter() { return this.wheelDiameter; }
    public Velocity getMaxSpeed() { return this.maxSpeed; }

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

        return driveMotor.getMotorType() != kBrushed && getDriveEncoder() != null && wheelDiameter.getValue() != 0;
    }
}
