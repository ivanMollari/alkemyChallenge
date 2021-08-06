package com.example.challengealkemy.DTO;

import com.example.challengealkemy.Models.MovieOrSerie;

import java.util.List;
import java.util.Set;

public class CartoonCharacterDetailsDTO {

    private Long id;
    private String name;
    private String urlImg;
    private Integer age;
    private Float weight;
    private String history;
    private List<MovieOrSerieDTO> listMoviesOrSeries;

    public CartoonCharacterDetailsDTO(Long id, String name, String urlImg, Integer age, Float weight, String history, List<MovieOrSerieDTO> listMoviesOrSeries) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.age = age;
        this.weight = weight;
        this.history = history;
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

    public List<MovieOrSerieDTO> getListMoviesOrSeries() {
        return listMoviesOrSeries;
    }

    public void setListMoviesOrSeries(List<MovieOrSerieDTO> listMoviesOrSeries) {
        this.listMoviesOrSeries = listMoviesOrSeries;
    }
}
