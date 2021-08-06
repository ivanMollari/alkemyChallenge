package com.example.challengealkemy.DTO;

public class CartoonCharacterDTO {

    private Long id;
    private String name;
    private String urlImg;
    private Integer age;
    private Float weight;
    private String history;

    public CartoonCharacterDTO(Long id, String name, String urlImg, Integer age, Float weight, String history) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.age = age;
        this.weight = weight;
        this.history = history;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
