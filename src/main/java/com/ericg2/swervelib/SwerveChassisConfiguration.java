package com.ericg2.swervelib;

import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.math.Velocity;
import com.kauailabs.navx.frc.AHRS;

public class SwerveChassisConfiguration {
    private Distance sideLength;
    private Velocity maxSpeed;
    private AHRS gyro;

    private SwerveModule flModule;
    private SwerveModule frModule;
    private SwerveModule blModule;
    private SwerveModule brModule;

    public SwerveChassisConfiguration setSideLength(Distance sideLength) {
        this.sideLength = sideLength;
        return this;
    }

    public SwerveChassisConfiguration setMaxSpeed(Velocity maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public SwerveChassisConfiguration setGyro(AHRS gyro) {
        this.gyro = gyro;
        return this;
    }

    public SwerveChassisConfiguration setFrontLeftModule(SwerveModule module) {
        this.flModule = module;
        return this;
    }

    public SwerveChassisConfiguration setFrontRightModule(SwerveModule module) {
        this.frModule = module;
        return this;
    }

    public SwerveChassisConfiguration setBackLeftModule(SwerveModule module) {
        this.blModule = module;
        return this;
    }

    public SwerveChassisConfiguration setBackRightModule(SwerveModule module) {
        this.brModule = module;
        return this;
    }

    public Distance getSideLength() { return this.sideLength; }
    public Velocity getMaxSpeed() { return this.maxSpeed; }
    public AHRS getGyro() { return this.gyro; }
    public SwerveModule getFrontLeftModule() { return this.flModule; }
    public SwerveModule getFrontRightModule() { return this.frModule; }
    public SwerveModule getBackLeftModule() { return this.blModule;}
    public SwerveModule getBackRightModule() { return this.brModule; }
}
