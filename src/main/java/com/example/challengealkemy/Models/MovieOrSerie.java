package com.example.challengealkemy.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class MovieOrSerie {

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
    @ManyToMany(mappedBy = "listMoviesOrSeries")
    private Set<Genre> listGenre;

    public MovieOrSerie() {
    }

    public MovieOrSerie(Long id, String title, String urlImg, LocalDate creationDate, Integer score, List<CartoonCharacter> listCartoonCharacters) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
        this.score = score;
        this.listCartoonCharacters = listCartoonCharacters;
    }

    public MovieOrSerie(String title, String urlImg, LocalDate creationDate, Integer score, List<CartoonCharacter> listCartoonCharacters) {
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
        this.score = score;
        this.listCartoonCharacters = listCartoonCharacters;
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

    public Set<Genre> getListGender() {
        return listGenre;
    }

    public void setListGender(Set<Genre> listGenre) {
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

