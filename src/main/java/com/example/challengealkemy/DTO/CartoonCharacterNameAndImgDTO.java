package com.example.challengealkemy.DTO;

public class CartoonCharacterNameAndImgDTO {

    private String name;
    private String urlImg;

    public CartoonCharacterNameAndImgDTO(String name, String urlImg) {
        this.name = name;
        this.urlImg = urlImg;
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
