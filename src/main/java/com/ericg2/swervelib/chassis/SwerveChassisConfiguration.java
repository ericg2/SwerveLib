package com.ericg2.swervelib.chassis;

import com.ericg2.swervelib.math.Distance;
import com.ericg2.swervelib.module.SwerveModule;
import com.ericg2.swervelib.module.SwerveModuleConfiguration;
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
            SwerveModuleConfiguration flModule,
            SwerveModuleConfiguration frModule,
            SwerveModuleConfiguration blModule,
            SwerveModuleConfiguration brModule,
            boolean gyroInverted) {
        this.sideLength = sideLength;
        this.gyro = gyro;
        this.flModule = new SwerveModule(flModule);
        this.frModule = new SwerveModule(frModule);
        this.blModule = new SwerveModule(blModule);
        this.brModule = new SwerveModule(brModule);
        this.gyroInverted = gyroInverted;
    }

    public Distance getSideLength() { return this.sideLength; }
    public AHRS getGyro() { return this.gyro; }
    public SwerveModule getFrontLeft() { return this.flModule; }
    public SwerveModule getFrontRight() { return this.frModule; }
    public SwerveModule getBackLeft() { return this.blModule;}
    public SwerveModule getBackRight() { return this.brModule; }
    public boolean isGyroInverted() { return this.gyroInverted; }
}
