package com.example.challengealkemy.Controllers;


import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSeries;
import com.example.challengealkemy.Services.MovieOrSeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieOrSeriesController {

    private final MovieOrSeriesServiceImpl movieOrSeriesService;

    @Autowired
    public MovieOrSeriesController(MovieOrSeriesServiceImpl movieOrSeriesService){
        this.movieOrSeriesService = movieOrSeriesService;
    }

    @GetMapping("/movies")
    public List<MovieOrSeriesTitleImgDateDTO> getMovies(){
        return movieOrSeriesService.getMoviesOrSeriesList();
    }

    @GetMapping("/movies/details")
    public List<MovieOrSeriesDetailsDTO> getDetailsOfMovieOrSeries(){
        return movieOrSeriesService.getAllAttributesOfMoviesOrSeries();
    }

    @PostMapping
    public void addNewMovieOrSeries(@RequestBody MovieOrSeries movieOrSeries){
        movieOrSeriesService.addNewMovieOrSeries(movieOrSeries);
    }

}
