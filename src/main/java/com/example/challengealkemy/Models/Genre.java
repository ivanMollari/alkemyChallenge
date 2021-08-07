package com.example.challengealkemy.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlImg;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "listGenre")
    private List<MovieOrSeries> listMoviesOrSeries;

    public Genre() {
    }

    public Genre(Long id, String name, String urlImg) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
    }

    public Genre(Long id, String name, String urlImg, List<MovieOrSeries> listMoviesOrSeries) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    public Genre(String name, String urlImg, List<MovieOrSeries> listMoviesOrSeries) {
        this.name = name;
        this.urlImg = urlImg;
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public List<MovieOrSeries> getListMoviesOrSeries() {
        return listMoviesOrSeries;
    }

    public void setListMoviesOrSeries(List<MovieOrSeries> listMoviesOrSeries) {
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

}

