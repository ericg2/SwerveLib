package com.ericg2.swervelib.sim;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;

public class AutoSparkMax extends CANSparkMax {
    private final AutoRelativeEncoder encoder;
    private double power = 0;

    /**
     * Create a new object to control a SPARK MAX motor Controller
     *
     * @param deviceId The device ID.
     * @param type     The motor type connected to the controller. Brushless motor wires must be connected
     *                 to their matching colors and the hall sensor must be plugged in. Brushed motors must be
     *                 connected to the Red and Black terminals only.
     */
    public AutoSparkMax(int deviceId, MotorType type) {
        super(deviceId, type);
        this.encoder = new AutoRelativeEncoder(this);
    }

    @Override
    public AutoRelativeEncoder getEncoder() {
        return encoder;
    }

    @Override
    public void set(double speed) {
        super.set(speed);
        power = MathUtil.clamp(speed, -1, 1);
    }

    @Override
    public double get() {
        double superPower = super.get();
        return RobotBase.isSimulation() ? power : superPower;
    }

    public void updateSim() {
        if (RobotState.isDisabled()) {
            power = 0;
        }
        encoder.updateSim();
    }
}
