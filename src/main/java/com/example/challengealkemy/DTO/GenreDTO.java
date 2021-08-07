package com.example.challengealkemy.DTO;

public class GenreDTO {

    private Long id;
    private String name;
    private String urlImg;

    public GenreDTO(Long id, String name, String urlImg) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
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
}
