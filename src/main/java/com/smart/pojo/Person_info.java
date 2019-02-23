package com.smart.pojo;

public class Person_info {
    private Body_parts body_parts;//人体节点
    private Location location;//检测矩形框
    public void setBody_parts(Body_parts body_parts) {
        this.body_parts = body_parts;
    }
    public Body_parts getBody_parts() {
        return body_parts;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }
}
