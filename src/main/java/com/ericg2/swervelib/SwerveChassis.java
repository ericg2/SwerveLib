package com.ericg2.swervelib;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import java.util.HashMap;
import java.util.Map;

public class SwerveChassis {
    private final SwerveDriveKinematics swerveKinematics;

    private static final String NAME_FL = "FL";
    private static final String NAME_FR = "FR";
    private static final String NAME_BL = "BL";
    private static final String NAME_BR = "BR";

    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;

    public SwerveChassis(SwerveModule frontLeft,
                         SwerveModule frontRight,
                         SwerveModule backLeft,
                         SwerveModule backRight,
                         double sideLength) {
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.backRight = backRight;
        this.backLeft = backLeft;
        
        swerveKinematics = new SwerveDriveKinematics(
                new Translation2d(sideLength / 2, sideLength / 2),
                new Translation2d(sideLength / 2, -sideLength / 2),
                new Translation2d(-sideLength / 2, sideLength / 2),
                new Translation2d(-sideLength / 2, -sideLength / 2)
        );
        
        updateDashboard();
    }

    private void updateDashboard() {
        frontLeft.updateDashboard(NAME_FL);
        frontRight.updateDashboard(NAME_FR);
        backLeft.updateDashboard(NAME_BL);
        backRight.updateDashboard(NAME_BR);
    }

    public SwerveModule getFrontLeft() {
        return frontLeft;
    }

    public SwerveModule getFrontRight() {
        return frontRight;
    }

    public SwerveModule getBackLeft() {
        return backLeft;
    }

    public SwerveModule getBackRight() {
        return backRight;
    }

    public SwerveDriveKinematics getSwerveKinematics() {
        return swerveKinematics;
    }

    public HashMap<String, SwerveModuleState> getSwerveModuleStates() {
        return new HashMap<>(Map.of(
                "FL", getFrontLeft().getState(),
                "BL", getBackLeft().getState(),
                "FR", getFrontRight().getState(),
                "BR", getBackRight().getState()
        ));
    }

    public SwerveModulePosition[] getSwerveModulePositions() {
        return new SwerveModulePosition[] {
                getFrontLeft().getPosition(),
                getFrontRight().getPosition(),
                getBackLeft().getPosition(),
                getBackRight().getPosition()
        };
    }

    public void setStates(SwerveModuleState[] states) {
        frontLeft.setState(states[0]);
        frontRight.setState(states[1]);
        backLeft.setState(states[2]);
        backRight.setState(states[3]);
    }

    public void drive(ChassisSpeeds speeds) {
        setStates(swerveKinematics.toSwerveModuleStates(speeds));
        updateDashboard();
    }

    public void resetDriveEncoders() {
        frontLeft.resetDriveEncoder();
        frontRight.resetDriveEncoder();
        backLeft.resetDriveEncoder();
        backRight.resetDriveEncoder();
    }

    public SwerveChassis getSwerveChassis(){
        return this;
    }
}