package com.course.dating.entity.Session;

import java.sql.Time;

public class SessionCodeEntity {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    private String code;
    private long created_time;

}
