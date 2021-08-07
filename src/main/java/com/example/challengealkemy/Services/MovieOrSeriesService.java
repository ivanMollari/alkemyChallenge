package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSeries;

import java.util.List;

public interface MovieOrSeriesService {

    MovieOrSeries getMovieById(Long id);

    List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesList();

    List<MovieOrSeriesDetailsDTO> getAllAttributesOfMoviesOrSeries();

    void addNewMovieOrSeries(MovieOrSeries newMovieOrSeries);
}