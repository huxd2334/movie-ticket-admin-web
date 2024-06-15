package com.binhlc.adminweb.dto;

import java.sql.Timestamp;

public class RoomDTO {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private Integer numCol, numRow, createBy, updateBy;
    private Timestamp timeCreate, timeUpdate;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumCol() {
        return numCol;
    }

    public void setNumCol(Integer numCol) {
        this.numCol = numCol;
    }

    public Integer getNumRow() {
        return numRow;
    }

    public void setNumRow(Integer numRow) {
        this.numRow = numRow;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
