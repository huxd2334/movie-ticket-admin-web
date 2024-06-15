package com.binhlc.adminweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "movie_time", schema = "movie_ticket")
public class MovieTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_movie")
    private Integer idMovie;

    @Column(name = "id_room")
    private Integer idRoom;

    @Column(name = "time_start")
    private Integer timeStart;

    @Column(name = "date_start")
    private Integer dateStart;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_by")
    private Integer createdBy;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_create")
    private Timestamp timeCreate;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_update")
    private Timestamp timeUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getDateStart() {
        return dateStart;
    }

    public void setDateStart(Integer dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
