package com.example.challengealkemy.services;

import com.example.challengealkemy.dto.MovieOrSeriesDetailsDTO;
import com.example.challengealkemy.dto.MovieOrSeriesTitleImgDateDTO;
import com.example.challengealkemy.models.MovieOrSeries;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface MovieOrSeriesService {

    Optional<MovieOrSeries> getMovieById(Long id);

    List<MovieOrSeriesTitleImgDateDTO> getMoviesOrSeriesList(HashMap<String, String> param);

    List<MovieOrSeriesDetailsDTO> getAllAttributesOfMoviesOrSeries();

    void addNewMovieOrSeries(MovieOrSeries newMovieOrSeries);

    void deleteMovieOrSeries(Long id);

    void updateMovieOrSeries(Long id, MovieOrSeries movieOrSeries);
}
