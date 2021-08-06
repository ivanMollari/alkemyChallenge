package com.example.challengealkemy.Controllers;

import com.example.challengealkemy.DTO.MovieOrSerieTitleImgDateDTO;
import com.example.challengealkemy.Services.MovieOrSerieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieOrSerieController {

    private final MovieOrSerieServiceImpl movieOrSerieService;

    @Autowired
    public MovieOrSerieController(MovieOrSerieServiceImpl movieOrSerieService){
        this.movieOrSerieService = movieOrSerieService;
    }

    @GetMapping("/movies")
    public List<MovieOrSerieTitleImgDateDTO> getMovies(){
        return movieOrSerieService.getMoviesOrSeriesList();
    }

}
