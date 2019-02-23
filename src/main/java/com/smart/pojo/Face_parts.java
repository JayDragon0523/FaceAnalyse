package com.smart.pojo;

import java.util.List;

public class Face_parts {
    private int person_num;//人数
    private List<Head_pose> headpose;//人脸姿势
    private List<Eyestatus> left_eyestatus;//左眼状态
    private List<Eyestatus> right_eyestatus;//右眼状态


    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }

    public List<Head_pose> getHeadpose() {
        return headpose;
    }

    public void setHeadpose(List<Head_pose> headpose) {
        this.headpose = headpose;
    }

    public List<Eyestatus> getLeft_eyestatus() {
        return left_eyestatus;
    }

    public void setLeft_eyestatus(List<Eyestatus> left_eyestatus) {
        this.left_eyestatus = left_eyestatus;
    }

    public List<Eyestatus> getRight_eyestatus() {
        return right_eyestatus;
    }

    public void setRight_eyestatus(List<Eyestatus> right_eyestatus) {
        this.right_eyestatus = right_eyestatus;
    }
}
