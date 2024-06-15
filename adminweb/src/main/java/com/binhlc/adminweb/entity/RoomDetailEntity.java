package com.binhlc.adminweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room_detail", schema = "movie_ticket")
public class RoomDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "roww")
    private Integer roww;

    @Column(name = "col")
    private Integer col;

    @Column(name = "type")
    private Integer type;

    @Column(name = "position")
    private Integer position;

    @Column(name = "room")
    private Integer room;

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoww(int i) {
        return roww;
    }

    public void setRoww(Integer roww) {
        this.roww = roww;
    }

    public Integer getCol(int i) {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPosition(int index) {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }
}
