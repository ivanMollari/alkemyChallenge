package com.example.challengealkemy.DTO;

import java.time.LocalDate;
import java.util.List;

public class MovieOrSeriesDetailsDTO {

    private Long id;
    private String title;
    private String urlImg;
    private LocalDate creationDate;
    private Integer score;
    private List<CartoonCharacterDTO> cartoonCharactersList;

    public MovieOrSeriesDetailsDTO(Long id, String title, String urlImg, LocalDate creationDate, Integer score, List<CartoonCharacterDTO> cartoonCharactersList) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
        this.score = score;
        this.cartoonCharactersList = cartoonCharactersList;
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

    public List<CartoonCharacterDTO> getCartoonCharactersList() {
        return cartoonCharactersList;
    }

    public void setCartoonCharactersList(List<CartoonCharacterDTO> cartoonCharactersList) {
        this.cartoonCharactersList = cartoonCharactersList;
    }
}
