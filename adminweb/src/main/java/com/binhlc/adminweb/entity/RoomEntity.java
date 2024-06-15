package com.binhlc.adminweb.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "room", schema = "movie_ticket")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private int idRoom;

    @Column(name = "name_room")
    private String name;

    @Column(name = "num_row")
    private Integer numRow;

    @Column(name = "num_col")
    private Integer numCol;

    @Column(name = "created_by")
    private Integer createBy;

    @Column(name = "time_create")
    private Timestamp timeCreate;

    @Column(name = "updated_by")
    private Integer updateBy;

    @Column(name = "time_update")
    private Timestamp timeUpdate;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumRow() {
        return numRow;
    }

    public void setNumRow(Integer numRow) {
        this.numRow = numRow;
    }

    public Integer getNumCol() {
        return numCol;
    }

    public void setNumCol(Integer numCol) {
        this.numCol = numCol;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
