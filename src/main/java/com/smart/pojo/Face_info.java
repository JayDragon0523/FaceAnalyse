package com.smart.pojo;

public class Face_info {
    private Face_parts face_parts;//人脸节点
    private Location location;//检测矩形框
    public void setFace_parts(Face_parts face_parts) {
        this.face_parts = face_parts;
    }
    public Face_parts getFace_parts() {
        return face_parts;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }
}
