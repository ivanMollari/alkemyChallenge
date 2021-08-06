package com.example.challengealkemy.Models;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class CartoonCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlImg;
    private Integer age;
    private Float weight;
    private String history;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "movieOrSerie_cartoonCharacter",
            joinColumns = @JoinColumn(name = "cartoonCharacter_id"),
            inverseJoinColumns = @JoinColumn(name = "movieOrSerie_id")
    )
    private List<MovieOrSerie> listMoviesOrSeries;

    public CartoonCharacter(String name, String urlImg, Integer age, Float weight, String history, List<MovieOrSerie> listMoviesOrSeries) {
        this.name = name;
        this.urlImg = urlImg;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    public CartoonCharacter(Long id, String name, String urlImg, Integer age, Float weight, String history, List<MovieOrSerie> listMoviesOrSeries) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    public CartoonCharacter() {

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

    public List<MovieOrSerie> getListMoviesOrSeries() {
        return listMoviesOrSeries;
    }

    public void setListMoviesOrSeries(List<MovieOrSerie> listMoviesOrSeries) {
        this.listMoviesOrSeries = listMoviesOrSeries;
    }

    @Override
    public String toString() {
        return "CartoonCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", history='" + history + '\'' +
                ", listMoviesOrSeries=" + listMoviesOrSeries +
                '}';
    }
}
