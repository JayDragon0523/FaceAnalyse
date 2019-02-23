package com.smart.pojo;

public class Head_pose {
    private double yaw_angle;//摇头
    private double roll_angle;//旋转（平面）
    private double pitch_angle;//抬头

    public double getYaw_angle() {
        return yaw_angle;
    }

    public void setYaw_angle(double yaw_angle) {
        this.yaw_angle = yaw_angle;
    }

    public double getRoll_angle() {
        return roll_angle;
    }

    public void setRoll_angle(double roll_angle) {
        this.roll_angle = roll_angle;
    }

    public double getPitch_angle() {
        return pitch_angle;
    }

    public void setPitch_angle(double pitch_angle) {
        this.pitch_angle = pitch_angle;
    }
}
