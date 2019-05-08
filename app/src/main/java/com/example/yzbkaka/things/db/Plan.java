package com.example.yzbkaka.things.db;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * Created by yzbkaka on 19-4-4.
 */

public class Plan extends LitePalSupport {
    int id;
    String writePlan;
    String year;
    String month;
    String day;
    boolean status;  //默认值为false
    Date createTime;  //创建的时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWritePlan() {
        return writePlan;
    }

    public void setWritePlan(String writePlan) {
        this.writePlan = writePlan;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
