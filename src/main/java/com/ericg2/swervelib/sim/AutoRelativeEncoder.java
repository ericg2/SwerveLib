package com.ericg2.swervelib.sim;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.RobotBase;

public class AutoRelativeEncoder implements RelativeEncoder {
    private CANSparkMax motor;

    private double simPosition = 0;
    private double simVelocity = 0;

    private boolean simInverted = false;

    public AutoRelativeEncoder(CANSparkMax motor) {
        this.motor = motor;
    }

    public void updateSim() {
        if (RobotBase.isSimulation()) {

            simVelocity = (motor.get() * 5676);
            simPosition += (simVelocity * 0.02);
        }
    }

    @Override
    public double getPosition() {
        return (RobotBase.isSimulation()) ? simPosition : motor.getEncoder().getPosition();
    }

    @Override
    public double getVelocity() {
        return (RobotBase.isSimulation()) ? simVelocity : motor.getEncoder().getVelocity();
    }

    @Override
    public REVLibError setPosition(double position) {
        if (RobotBase.isSimulation()) {
            this.simPosition = position;
            return REVLibError.kOk;
        } else {
            return motor.getEncoder().setPosition(position);
        }
    }

    @Override
    public REVLibError setPositionConversionFactor(double factor) {
        if (RobotBase.isSimulation())
            return REVLibError.kOk;
        return motor.getEncoder().setPositionConversionFactor(factor);
    }

    @Override
    public REVLibError setVelocityConversionFactor(double factor) {
        if (RobotBase.isSimulation())
            return REVLibError.kOk;
        return motor.getEncoder().setVelocityConversionFactor(factor);
    }

    @Override
    public double getPositionConversionFactor() {
        return (RobotBase.isSimulation()) ? 0 : motor.getEncoder().getPositionConversionFactor();
    }

    @Override
    public double getVelocityConversionFactor() {
        return (RobotBase.isSimulation()) ? 0 : motor.getEncoder().getVelocityConversionFactor();
    }

    @Override
    public REVLibError setAverageDepth(int depth) {
        if (RobotBase.isSimulation())
            return REVLibError.kOk;
        return motor.getEncoder().setAverageDepth(depth);
    }

    @Override
    public int getAverageDepth() {
        return (RobotBase.isSimulation()) ? 0 : motor.getEncoder().getAverageDepth();
    }

    @Override
    public REVLibError setMeasurementPeriod(int period_ms) {
        if (RobotBase.isSimulation())
            return REVLibError.kOk;
        return motor.getEncoder().setMeasurementPeriod(period_ms);
    }

    @Override
    public int getMeasurementPeriod() {
        return (RobotBase.isSimulation()) ? 0 : motor.getEncoder().getMeasurementPeriod();
    }

    @Override
    public int getCountsPerRevolution() {
        return (RobotBase.isSimulation()) ? 0 : motor.getEncoder().getCountsPerRevolution();
    }

    @Override
    public REVLibError setInverted(boolean inverted) {
        if (RobotBase.isSimulation()) {
            this.simInverted = inverted;
            return REVLibError.kOk;
        }
        return motor.getEncoder().setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return (RobotBase.isSimulation()) ? simInverted : motor.getEncoder().getInverted();
    }
}
