package com.binhlc.adminweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_category", schema = "movie_ticket")
public class MovieCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "id_movie")
    private Integer idMovie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }
}
