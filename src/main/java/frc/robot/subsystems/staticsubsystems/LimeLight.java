package frc.robot.subsystems.staticsubsystems;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.util.NetworkTablesUtil;

public class LimeLight {
    private static final PIDController clawRotationPID;
    private static final float kp = 0.003125f;
    private static final float ki = 0.01f;
    private static final float kd = 0f;

    static {
        clawRotationPID = new PIDController(1, ki, kd);
    }

    public static void poke() {
        System.out.println("LimeLight initialized");
    }

    public LimeLight() {
    }

    public static float getXAdjustment() {
        float tx = (NetworkTablesUtil.getLimeLightErrorX() - 160) * kp;

        // if tx is too big, return the max of 1 or -1
        if (Math.abs(tx) > 1) {
            // return 1 if tx is greater than 1, -1 if tx is less than -1
            return Math.copySign(1, tx);
        }
        return tx;
    }

    public static float getYAdjustment() {
        float ty = (NetworkTablesUtil.getLimeLightErrorY() - 120) * kp;

        // if ty is too big, return the max of 1 or -1
        if (Math.abs(ty) > 1) {
            // return 1 if ty is greater than 1, -1 if ty is less than -1
            return Math.copySign(1, ty);
        }
        return ty;
    }

    public static float getArea() {
        float tA = (NetworkTablesUtil.getLimeLightArea());
        return tA;
    }

    public static double getAngleAdjustment() {
        float angle = (NetworkTablesUtil.getConeOrientation()) * kp;

        // if angle is too big, return the max of 1 or -1
        if (Math.abs(angle) > 1) {
            // return 1 if angle is greater than 1, -1 if angle is less than -1
            return Math.copySign(1, angle);
        }
        // calculate the PID for the steering adjustment
        return -clawRotationPID.calculate(angle, angle > 180 ? 360 : 0); // Negated because claw rotation angle is inversely related to cone orientation angle
        // If cone angle measures greater than 180 (tip pointing towards right), it goes towards 360. If it measures less than 180 (tip pointing towards left), it goes towards 0. (360 and 0 both represent the cone pointing straight up)
    }

    public static void setIntendedAngle(double setpoint) {
        clawRotationPID.setSetpoint(setpoint);
    }
}