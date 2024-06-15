package com.binhlc.adminweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket", schema = "movie_cinema")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private int idTicket;

    @Column(name = "id_room_detail")
    private Integer idRoomDetail;

    @Column(name = "id_movie_time")
    private Integer idMovieTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_by")
    private Integer createBy;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_create")
    private Timestamp timeCreate;

    @Column(name = "updated_by")
    private Integer updateBy;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_update")
    private Timestamp timeUpdate;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdRoomDetail() {
        return idRoomDetail;
    }

    public void setIdRoomDetail(Integer idRoomDetail) {
        this.idRoomDetail = idRoomDetail;
    }

    public Integer getIdMovieTime() {
        return idMovieTime;
    }

    public void setIdMovieTime(Integer idMovieTime) {
        this.idMovieTime = idMovieTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
