package com.ericg2.swervelib.module;

public enum SwerveModuleSide {
    NONE,
    FRONT_LEFT,
    FRONT_RIGHT,
    BACK_LEFT,
    BACK_RIGHT;
    @Override
    public String toString() {
        return switch (this) {
            case FRONT_LEFT -> "FL";
            case FRONT_RIGHT -> "FR";
            case BACK_LEFT -> "BL";
            case BACK_RIGHT -> "BR";
            default -> "NONE";
        };
    }
}
