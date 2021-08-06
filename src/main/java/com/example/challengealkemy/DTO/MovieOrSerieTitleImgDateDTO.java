package com.example.challengealkemy.DTO;

import java.time.LocalDate;

public class MovieOrSerieTitleImgDateDTO {

    private String title;
    private String urlImg;
    private LocalDate creationDate;

    public MovieOrSerieTitleImgDateDTO(String title, String urlImg, LocalDate creationDate) {
        this.title = title;
        this.urlImg = urlImg;
        this.creationDate = creationDate;
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
}
