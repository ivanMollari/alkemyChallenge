package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.MovieOrSerieDTO;
import com.example.challengealkemy.DTO.MovieOrSerieTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSerie;

import java.util.List;

public interface MovieOrSerieService{

    MovieOrSerie getMovieById(Long id);

    List<MovieOrSerieTitleImgDateDTO> getMoviesOrSeriesList();

    List<MovieOrSerieDTO> getMoviesOrSeriesOfaCharacter(List<MovieOrSerie> movieOrSerieList);

}
