package com.example.challengealkemy.Controllers;


import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSeries;
import com.example.challengealkemy.Services.MovieOrSeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieOrSeriesController {

    private final MovieOrSeriesServiceImpl movieOrSeriesService;

    @Autowired
    public MovieOrSeriesController(MovieOrSeriesServiceImpl movieOrSeriesService){
        this.movieOrSeriesService = movieOrSeriesService;
    }

    @GetMapping("/movies")
    public List<MovieOrSeriesTitleImgDateDTO> getMovies(@RequestParam HashMap<String, String> param){

        return movieOrSeriesService.getMoviesOrSeriesList(param);
    }

    @GetMapping("/movies/details")
    public List<MovieOrSeriesDetailsDTO> getDetailsOfMovieOrSeries(){
        return movieOrSeriesService.getAllAttributesOfMoviesOrSeries();
    }

    @PostMapping("/movies/add")
    public void addNewMovieOrSeries(@RequestBody MovieOrSeries movieOrSeries){
        movieOrSeriesService.addNewMovieOrSeries(movieOrSeries);
    }

    @DeleteMapping("/movies/delete/{idMovieOrSeries}")
    public void deleteMovieOrSeries(@PathVariable("idMovieOrSeries")Long id){
        movieOrSeriesService.deleteMovieOrSeries(id);
    }

    @PutMapping("movies/update/{idMoviesOrSeries}")
    public void updateMovieOrSeries(@PathVariable("idMoviesOrSeries")Long id,
                                    @RequestBody MovieOrSeries movieOrSeries){
        movieOrSeriesService.updateMovieOrSeries(id,movieOrSeries);

    }
}
