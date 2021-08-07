package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.DTO.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSeries;

import java.util.HashMap;
import java.util.List;

public interface MovieOrSeriesService {

    MovieOrSeries getMovieById(Long id);

    List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesList(HashMap<String, String> param);

    List<MovieOrSeriesDetailsDTO> getAllAttributesOfMoviesOrSeries();

    void addNewMovieOrSeries(MovieOrSeries newMovieOrSeries);

    void deleteMovieOrSeries(Long id);

    void updateMovieOrSeries(Long id, MovieOrSeries movieOrSeries);
}
