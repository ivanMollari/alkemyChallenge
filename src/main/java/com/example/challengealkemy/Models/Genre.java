package com.example.challengealkemy.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlImg;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "gender_movieOrSerie",
            joinColumns = {@JoinColumn(name = "gender_id")},
            inverseJoinColumns = {@JoinColumn(name = "movieOrSerie")}
    )
    private Set<MovieOrSerie> listMoviesOrSeries;

    public Genre() {
    }

    public Genre(Long id, String name, String urlImg, Set<MovieOrSerie> listMoviesOrSeries) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    public Genre(String name, String urlImg, Set<MovieOrSerie> listMoviesOrSeries) {
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

    public Set<MovieOrSerie> getListMoviesOrSeries() {
        return listMoviesOrSeries;
    }

    public void setListMoviesOrSeries(Set<MovieOrSerie> listMoviesOrSeries) {
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

}

