package com.ericg2.swervelib.sim;

import com.ericg2.swervelib.math.GearRatio;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.RobotBase;

import java.security.InvalidParameterException;

public class AutoDutyCycleEncoder extends DutyCycleEncoder {
    private AutoSparkMax motor;
    private double simPosition;
    private GearRatio turnRatio;

    private SlewRateLimiter limiter;

    /**
     * @deprecated
     * Do not use this Constructor. Use {@link #AutoDutyCycleEncoder(int, AutoSparkMax)} instead.
     * @throws InvalidParameterException On attempt to use this version.
     */
    public AutoDutyCycleEncoder(int channel) {
        super(channel);
        throw new InvalidParameterException();
    }

    public AutoDutyCycleEncoder(int channel, AutoSparkMax motor, GearRatio turnRatio) {
        super(channel);
        this.motor = motor;
        this.turnRatio = turnRatio;
        this.limiter = new SlewRateLimiter(30);
    }

    public void updateSim() {
        if (RobotBase.isSimulation() && motor != null) {
            // A turn ratio is necessary due to the encoder measuring the position of the wheel, rather than the
            // motor directly.
            double motorRPM = motor.getEncoder().getVelocity();

            double adjusted = limiter.calculate(turnRatio.motorRotationsToAngle(motorRPM).getRotations());

            simPosition += (adjusted * 0.02);
        }
    }

    @Override
    public double get() {
        return (RobotBase.isSimulation() && motor != null) ? simPosition : super.get();
    }
}
