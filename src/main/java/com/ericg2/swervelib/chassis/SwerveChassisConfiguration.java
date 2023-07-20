package com.ericg2.swervelib.chassis;

import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.module.SwerveModule;
import com.kauailabs.navx.frc.AHRS;

public class SwerveChassisConfiguration {
    private Distance sideLength;
    private AHRS gyro;
    private SwerveModule flModule;
    private SwerveModule frModule;
    private SwerveModule blModule;
    private SwerveModule brModule;

    private boolean gyroInverted = true;

    public SwerveChassisConfiguration setSideLength(Distance sideLength) {
        this.sideLength = sideLength;
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

    public SwerveChassisConfiguration setGyroInverted(boolean inverted) {
        this.gyroInverted = inverted;
        return this;
    }

    public Distance getSideLength() { return this.sideLength; }
    public AHRS getGyro() { return this.gyro; }
    public SwerveModule getFrontLeft() { return this.flModule; }
    public SwerveModule getFrontRight() { return this.frModule; }
    public SwerveModule getBackLeft() { return this.blModule;}
    public SwerveModule getBackRight() { return this.brModule; }
    public boolean isGyroInverted() { return this.gyroInverted; }

    public boolean validate() {
        if (sideLength == null)
            return false;
        if (gyro == null)
            return false;
        if (flModule == null)
            return false;
        if (frModule == null)
            return false;
        if (blModule == null)
            return false;
        if (brModule == null)
            return false;

        if (sideLength.toMeters() <= 0)
            return false;

        return gyro.isConnected();
    }
}
