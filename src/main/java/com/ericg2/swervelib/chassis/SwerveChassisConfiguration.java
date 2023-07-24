package com.ericg2.swervelib.chassis;

import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.module.SwerveModule;
import com.kauailabs.navx.frc.AHRS;

public class SwerveChassisConfiguration {
    private final Distance sideLength;
    private final AHRS gyro;
    private final SwerveModule flModule;
    private final SwerveModule frModule;
    private final SwerveModule blModule;
    private final SwerveModule brModule;

    private final boolean gyroInverted;

    public SwerveChassisConfiguration(
            Distance sideLength,
            AHRS gyro,
            SwerveModule flModule,
            SwerveModule frModule,
            SwerveModule blModule,
            SwerveModule brModule,
            boolean gyroInverted) {
        this.sideLength = sideLength;
        this.gyro = gyro;
        this.flModule = flModule;
        this.frModule = frModule;
        this.blModule = blModule;
        this.brModule = brModule;
        this.gyroInverted = gyroInverted;
    }

    public SwerveChassisConfiguration(
            Distance sideLength,
            AHRS gyro,
            SwerveModule flModule,
            SwerveModule frModule,
            SwerveModule blModule,
            SwerveModule brModule) {
        this(
                sideLength,
                gyro,
                flModule,
                frModule,
                blModule,
                brModule,
                false
        );
    }

    /*
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

     */

    public Distance getSideLength() { return this.sideLength; }
    public AHRS getGyro() { return this.gyro; }
    public SwerveModule getFrontLeft() { return this.flModule; }
    public SwerveModule getFrontRight() { return this.frModule; }
    public SwerveModule getBackLeft() { return this.blModule;}
    public SwerveModule getBackRight() { return this.brModule; }
    public boolean isGyroInverted() { return this.gyroInverted; }
}
