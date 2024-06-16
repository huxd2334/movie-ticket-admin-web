package com.binhlc.adminweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "movie", schema = "movie_ticket")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "language")
    private String language;

    @Column(name = "status")
    private Integer status;

    @Column(name = "part_time")
    private Integer releasedDate;

    @Column(name = "time")
    private Integer duration;

    @Column(name = "censor")
    private Integer censor;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "thumb")
    private String thumbnail;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "actor", columnDefinition = "TEXT")
    private String actor;

    @Column(name = "director")
    private String director;

    @Column(name = "dubbing")
    private Integer dubbing;

    @Column(name = "view")
    private long view;

    @Column(name = "storyline", columnDefinition = "TEXT")
    private String storyLine;

    @Column(name = "created_by")
    private int createdBy;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_create")
    private Timestamp timeCreate;

    @Column(name = "updated_by")
    private int updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_update")
    private Timestamp timeUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Integer releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCensor() {
        return censor;
    }

    public void setCensor(Integer censor) {
        this.censor = censor;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getDubbing() {
        return dubbing;
    }

    public void setDubbing(Integer dubbing) {
        this.dubbing = dubbing;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
