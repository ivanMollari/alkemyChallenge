package com.example.challengealkemy.controllers;


import com.example.challengealkemy.dto.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.dto.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.models.MovieOrSeries;
import com.example.challengealkemy.services.MovieOrSeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieOrSeriesController {

    private final MovieOrSeriesServiceImpl movieOrSeriesService;

    @Autowired
    public MovieOrSeriesController(MovieOrSeriesServiceImpl movieOrSeriesService){
        this.movieOrSeriesService = movieOrSeriesService;
    }

    @GetMapping
    public List<MovieOrSeriesTitleImgDateDTO> getMovies(@RequestParam HashMap<String, String> param){

        return movieOrSeriesService.getMoviesOrSeriesList(param);
    }

    @GetMapping("/details")
    public List<MovieOrSeriesDetailsDTO> getDetailsOfMovieOrSeries(){
        return movieOrSeriesService.getAllAttributesOfMoviesOrSeries();
    }

    @PostMapping
    public void addNewMovieOrSeries(@RequestBody MovieOrSeries movieOrSeries){
        movieOrSeriesService.addNewMovieOrSeries(movieOrSeries);
    }

    @DeleteMapping("/{idMovieOrSeries}")
    public void deleteMovieOrSeries(@PathVariable("idMovieOrSeries")Long id){
        movieOrSeriesService.deleteMovieOrSeries(id);
    }

    @PutMapping("/{idMoviesOrSeries}")
    public void updateMovieOrSeries(@PathVariable("idMoviesOrSeries")Long id,
                                    @RequestBody MovieOrSeries movieOrSeries){
        movieOrSeriesService.updateMovieOrSeries(id,movieOrSeries);

    }
}
