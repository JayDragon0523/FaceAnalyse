package com.smart.pojo;

import java.util.List;

public class FaceAnalysisBean {
    //人数
    private int person_num;
    //人体姿态信息
    private List<Face_info> face_info;
    //唯一的log id，用于问题定位
    private long log_id;
    public int getPerson_num() {
        return person_num;
    }
    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }
    public List<Face_info> getFace_info() {
        return face_info;
    }
    public void setFace_info(List<Person_info> person_info) {
        this.face_info = face_info;
    }
    public long getLog_id() {
        return log_id;
    }
    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }
}
