package com.example.challengealkemy.Services;

import com.example.challengealkemy.DTO.MovieOrSerieDTO;
import com.example.challengealkemy.DTO.MovieOrSerieTitleImgDateDTO;
import com.example.challengealkemy.Models.MovieOrSerie;
import com.example.challengealkemy.Repositories.MovieOrSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieOrSerieServiceImpl implements MovieOrSerieService{

    private final MovieOrSerieRepository movieOrSerieRepository;

    @Autowired
    public MovieOrSerieServiceImpl(MovieOrSerieRepository movieOrSerieRepository){
        this.movieOrSerieRepository = movieOrSerieRepository;
    }


    @Override
    public MovieOrSerie getMovieById(Long id) {
        return movieOrSerieRepository.getById(id);
    }

    @Override
    public List<MovieOrSerieTitleImgDateDTO> getMoviesOrSeriesList() {
        List<MovieOrSerie> listMoviesOrSeries = movieOrSerieRepository.findAll();
        List<MovieOrSerieTitleImgDateDTO> movieOrSerieTitleImgDateDTOList = new ArrayList<>();

        for(MovieOrSerie movieOrSerie : listMoviesOrSeries){
            MovieOrSerieTitleImgDateDTO newMovieOrSerie = new MovieOrSerieTitleImgDateDTO(
                    movieOrSerie.getTitle(),
                    movieOrSerie.getUrlImg(),
                    movieOrSerie.getCreationDate()
            );
            movieOrSerieTitleImgDateDTOList.add(newMovieOrSerie);
        }

        return movieOrSerieTitleImgDateDTOList;
    }

    @Override
    public List<MovieOrSerieDTO> getMoviesOrSeriesOfaCharacter(List<MovieOrSerie> movieOrSerieList) {
        List<MovieOrSerieDTO> movieOrSerieDTOList = new ArrayList<>();

        for(MovieOrSerie movieOrSerie : movieOrSerieList){
            MovieOrSerieDTO newMovieOrSerie = new MovieOrSerieDTO(
                    movieOrSerie.getId(),
                    movieOrSerie.getTitle(),
                    movieOrSerie.getUrlImg(),
                    movieOrSerie.getCreationDate(),
                    movieOrSerie.getScore()
            );
            movieOrSerieDTOList.add(newMovieOrSerie);
        }
        return movieOrSerieDTOList;
    }
}
