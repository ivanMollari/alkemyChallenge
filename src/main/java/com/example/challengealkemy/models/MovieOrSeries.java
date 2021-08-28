package com.example.challengealkemy.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class MovieOrSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String urlImg;
    private LocalDate creationDate;
    private Integer score;
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },
                mappedBy = "listMoviesOrSeries")
    private List<CartoonCharacter> listCartoonCharacters;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "movieOrSeries_genre",
            joinColumns = @JoinColumn(name = "movieOrSeries_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> listGenre;

    public MovieOrSeries(Long id, String title, String urlImg, LocalDate creationDate, Integer score, List<CartoonCharacter> listCartoonCharacters,List<Genre> genreList) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
        this.score = score;
        this.listCartoonCharacters = listCartoonCharacters;
        this.listGenre = genreList;
    }

    public MovieOrSeries(String title, String urlImg, LocalDate creationDate, Integer score, List<CartoonCharacter> listCartoonCharacters, List<Genre> genreList) {
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
        this.score = score;
        this.listCartoonCharacters = listCartoonCharacters;
        this.listGenre = genreList;
    }

    public MovieOrSeries() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CartoonCharacter> getListCartoonCharacters() {
        return listCartoonCharacters;
    }

    public void setListCartoonCharacters(List<CartoonCharacter> listCartoonCharacters) {
        this.listCartoonCharacters = listCartoonCharacters;
    }

    public List<Genre> getListGender() {
        return listGenre;
    }

    public void setListGender(List<Genre> listGenre) {
        this.listGenre = listGenre;
    }

    @Override
    public String toString() {
        return "MovieOrSerie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", creationDate=" + creationDate +
                ", score=" + score +
                ", listCartoonCharacters=" + listCartoonCharacters +
                ", listGenre=" + listGenre +
                '}';
    }
}

